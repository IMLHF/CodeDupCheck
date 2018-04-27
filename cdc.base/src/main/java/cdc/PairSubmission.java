package cdc;

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
        float sa = subA.size() - 1;
        float sb = subB.size() - 1;
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



}
