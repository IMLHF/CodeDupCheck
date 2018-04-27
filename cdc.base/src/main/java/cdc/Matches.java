package cdc;

public class Matches {
    public Match[] matches;

    private int matchesNum;//最大公共子串的数量(一个token为一个字符
    private final int increment=20;

    public Matches(){
        matches=new Match[10];
        for(int i=0;i<10;++i)
            matches[i]=new Match();
        matchesNum=0;
    }

    public final int matchesNum(){
        return matchesNum;
    }

    public final void ensureCapacity(int minCapacity){
        int oldCapcity=matches.length;
        if(minCapacity>oldCapcity){
            Match[] oldMatches=matches;
            int newCapacity=(oldCapcity+increment);
            if(newCapacity<minCapacity)
                newCapacity=minCapacity;
            matches=new Match[newCapacity];
            System.arraycopy(oldMatches,0,matches,0,oldCapcity);
            for (int i = oldCapcity; i < newCapacity; i++) {
                matches[i]=new Match();
            }
        }
    }

    public  final  void addMatch(int startA,int startB,int length){
        for(int i=matchesNum-1;i>=0;i--){
            if(matches[i].overlap(startA,startB,length)) return;
        }
        ensureCapacity(matchesNum+1);
        matches[matchesNum].set(startA,startB,length);
        matchesNum++;
    }
    public  final void clear(){
        matchesNum=0;
    }
}
