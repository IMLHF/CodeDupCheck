package cdc;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;

public class PairSubmission extends Matches implements Comparator<PairSubmission> {
    public Submission subA;
    public Submission subB;

    public PairSubmission(Submission subA, Submission subB) {
        super();
        this.subA = subA;
        this.subB = subB;
    }

    public final void sort_permutation(int s) {
    }

    public final void sort() {
        Arrays.sort(matches, 0, this.matchesNum());
    }

    public final int tokensMatched() {
        int erg = 0;
        for (int i = 0; i < matchesNum(); ++i) {
            erg += matches[i].length;
        }
        return erg;
    }

    private final int biggestMatch() {
        int erg = 0;
        for (int i = 0; i < matchesNum(); i++)
            if (matches[i].length > erg) erg = matches[i].length;
        return erg;
    }

    public final float percent() {
        float sa = subA.tokenLength() - 1;
        float sb = subB.tokenLength() - 1;
        return ((float)tokensMatched()*200)/(sa+sb);
    }

    ;

    public int compare(PairSubmission o1, PairSubmission o2) {
        float p1 = o1.percent();
        float p2 = o2.percent();
        if (p1 == p2) return 0;
        if (p1 > p2)
            return -1;
        else
            return 1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PairSubmission)) return false;
        return (compare(this, (PairSubmission) obj) == 0);
    }

    public String toString() {
        return subA.name + " <-> " + subB.name;
    }

    public static class AvgComparator implements Comparator<PairSubmission> {
        public int compare(PairSubmission o1, PairSubmission o2) {
            float p1 = o1.percent();
            float p2 = o2.percent();
            if (p1 == p2) return 0;
            if (p1 > p2)
                return -1;
            else
                return 1;
        }
    }

    /********************
     * MaxComparator
     * @return
     */
    public final float percentA() {
        int divisor=0;
//        if(bcmatchesA != null) divisor = subA.size()-subA.files.length-bcmatchesA.tokensMatched();
//        else divisor = subA.size()-subA.files.length;
        return (divisor == 0 ? 0f : (tokensMatched()*100 / (float) divisor));
    }
    public final float percentB() {
        int divisor=0;
//        if(bcmatchesB != null) divisor = subB.size()-subB.files.length-bcmatchesB.tokensMatched();
//        else divisor = subB.size()-subB.files.length;
        return (divisor == 0 ? 0f : (tokensMatched()*100 / (float) divisor));
    }
    public final float percentMaxAB() {
        float a=percentA();
        float b=percentB();
        if(a>b) return a;
        else return b;
    }
    public static class MaxComparator implements Comparator<PairSubmission> {
        public int compare(PairSubmission o1, PairSubmission o2) {
            float p1 = o1.percentMaxAB();
            float p2 = o2.percentMaxAB();
            if (p1 == p2) return 0;
            if (p1 > p2)
                return -1;
            else
                return 1;
        }
    }



}
