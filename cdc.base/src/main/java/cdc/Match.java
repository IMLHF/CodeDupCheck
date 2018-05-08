package cdc;

public class Match implements Comparable<Match>{
    public int startA;
    public int startB;
    public int length;

    public Match(int startA,int startB,int length){
        this.startA=startA;
        this.startB=startB;
        this.length=length;
    }

    public Match(){}

    public void set(int startA,int startB,int length){
        this.startA=startA;
        this.startB=startB;
        this.length=length;
    }

    public final boolean contains(int index,int sub){
        int start =(sub==0?startA:startB);
        return (start<=index && index<(start+length));
    }

    public final boolean overlap(int oStartA,int oStartB,int oLength){
        if(startA<oStartA){
            if((oStartA-startA)<length) return true;
        }else {
            if((startA-oStartA)<oLength)return true;
        }
        if (startB < oStartB) {
            if ((oStartB - startB) < length) return true;
        } else {
            if ((startB - oStartB) < oLength) return true;
        }
        return false;
    }

    public int compareTo(Match o) {
        if(this.startA<o.startA)
            return -1;
        else if(this.startA>o.startA)
            return  1;
        return 0;
    }
}
