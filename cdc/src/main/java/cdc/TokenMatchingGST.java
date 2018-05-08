package cdc;

import javafx.print.PageOrientation;

public class TokenMatchingGST implements TokenConstants {
    private Matches matches = new Matches();
    private Program program;

    public TokenMatchingGST(Program program) {
        this.program = program;
    }

    public void create_hashes(Structure s, int hashLength, boolean makeTable) {
        if (hashLength < 1) hashLength = 1;
        hashLength = (hashLength < 26 ? hashLength : 25);

        if (s.tokenLength() < hashLength) return;

        int modulo = ((1 << 6) - 1);   // Modulo 64!

        int loops = s.tokenLength() - hashLength;
        s.table = (makeTable ? new Table(3 * loops) : null);
        int hash = 0;
        int i;
        int hashedLength = 0;
        for (i = 0; i < hashLength; i++) {
            hash = (2 * hash) + (s.tokens[i].type & modulo);
            hashedLength++;
            if (s.tokens[i].marked)
                hashedLength = 0;
        }
        int factor = (hashLength != 1 ? (2 << (hashLength - 2)) : 1);

        if (makeTable) {
            for (i = 0; i < loops; i++) {
                if (hashedLength >= hashLength) {
                    s.tokens[i].hash = hash;
                    s.table.add(hash, i);   // add into hashtable
                } else
                    s.tokens[i].hash = -1;
                hash -= factor * (s.tokens[i].type & modulo);
                hash = (2 * hash) + (s.tokens[i + hashLength].type & modulo);
                if (s.tokens[i + hashLength].marked)
                    hashedLength = 0;
                else
                    hashedLength++;
            }
        } else {
            for (i = 0; i < loops; i++) {
                s.tokens[i].hash = (hashedLength >= hashLength) ? hash : -1;
                hash -= factor * (s.tokens[i].type & modulo);
                hash = (2 * hash) + (s.tokens[i + hashLength].type & modulo);
                if (s.tokens[i + hashLength].marked)
                    hashedLength = 0;
                else
                    hashedLength++;
            }
        }
        s.hash_length = hashLength;
    }

    public final PairSubmission compare(Submission subA, Submission subB) {
        Submission A, B, tmp;
        if (subA.struct.tokenLength() > subB.struct.tokenLength()) {
            A = subB;
            B = subA;
        } else {
            A = subA;
            B = subB;
        }
        // if hashtable exists in first but not in second structure: flip around!
        if (B.struct.table == null && A.struct.table != null) {
            tmp = A;
            A = B;
            B = tmp;
        }

        return compare(A, B, this.program.get_min_token_match());
    }

    //MML :最长子串的最小匹配
    private PairSubmission compare(Submission subA, Submission subB, int MML) {
        Structure structA = subA.struct;
        Structure structB = subB.struct;

        Token[] strA = structA.tokens;
        Token[] strB = structB.tokens;
        int tokenlengthA = structA.tokenLength();
        int tokenlengthB = structB.tokenLength();
        PairSubmission pairSubmission = new PairSubmission(subA, subB);

        for (int i = 0; i < tokenlengthA; i++)
            strA[i].marked = strA[i].type == FILE_END || strA[i].type == SEPARATOR_TOKEN;

        for (int i = 0; i < tokenlengthB; i++)
            strB[i].marked = strB[i].type == FILE_END || strB[i].type == SEPARATOR_TOKEN;

        if (tokenlengthA < MML || tokenlengthB < MML)
            return pairSubmission;


        if (structA.hash_length != this.program.get_min_token_match()) {
            create_hashes(structA, MML, false);
        }
        if (structB.hash_length != this.program.get_min_token_match()
                || structB.table == null) {
            create_hashes(structB, MML, true);
        }
        int maxmatch;
        int[] elemsB;

        do {
            maxmatch = MML;
            matches.clear();
            for (int x = 0; x < tokenlengthA - maxmatch; ++x) {
                if (strA[x].marked || strA[x].hash == -1
                        || (elemsB = structB.table.get(strA[x].hash)) == null)
                    continue;
                loopB:
                for (int i = 1; i <= elemsB[0]; ++i) {
                    int y = elemsB[i];
                    if (strB[y].marked || maxmatch > tokenlengthB - y) continue;

                    //查看前maxmatch个是否匹配
                    int j, hx, hy;
                    for (j = maxmatch - 1; j >= 0; --j) {
                        if (strA[hx = x + j].type != strB[hy = y + j].type || strA[hx].marked || strB[hy].marked)
                            continue loopB;
                    }
                    //扩大匹配
                    j = maxmatch;
                    while (strA[hx = x + j].type == strB[hy = y + j].type
                            && !strA[hx].marked && !strB[hy].marked)
                        j++;
                    if (j == maxmatch)
                        matches.addMatch(x, y, j);
                    else if (j > maxmatch) {
                        matches.clear();
                        matches.addMatch(x,y,j);
                        maxmatch = j;
                    }
                }
            }
            for( int i=matches.matchesNum()-1;i>=0;--i){
                int x=matches.matches[i].startA;
                int y=matches.matches[i].startB;
                int len=matches.matches[i].length;
                pairSubmission.addMatch(x,y,len);
                for(int j=len;j>0;--j){
                    strA[x++].marked=strB[y++].marked=true;
                }
            }
        }while(maxmatch!=MML);
        //System.out.println("++++++++++++++++++num  "+pairSubmission.matchesNum());
        return pairSubmission;
    }
}
