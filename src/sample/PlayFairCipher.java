package sample;

public class PlayFairCipher {
    private char[][] grid=new char[5][5];
    private char letterSkip;
    private String key;

    PlayFairCipher(char letterSkip,String key)
    {
        this.setLetterSkip(letterSkip);
        this.setKey(key);
        this.setGrid();
    }

    public void setKey(String key) {
        key=key.replaceAll(" ","");
        key=key.toUpperCase();
        this.key = key;
    }


    public boolean validateKey()
    {
        if(this.key.length() > 26 || !this.key.matches("[A-Z]+"))
        {
            return false;
        }
        return true;
    }

    public String encrypt(String text)
    {
        String encryption="";

        text=text.replaceAll(" ","");
        text=text.toUpperCase();



        for (int i = 0; i < text.length()-1; i++) {
            if(text.charAt(i) == text.charAt(i+1))
            {
                StringBuilder tempTxt=new StringBuilder(text);
                tempTxt.insert(i+1,'X');
                text=tempTxt.toString();
            }
        }

        if (text.length()%2!=0)
            text+="X";



        if(text.indexOf(this.letterSkip) != -1)
        {
            String randomStr=(char)('A' + Math.random() * ('Z' - 'A' ))+"";
            text=text.replaceAll(this.letterSkip+"",randomStr);
            System.out.printf("Found %c replaced by  %s \n",this.letterSkip,randomStr);
        }



        for (int i = 0; i < text.length(); i+=2) {
            int[][] position=new int[2][2];

            for (int j = 0; j < this.grid.length; j++) {
                for (int k = 0; k < this.grid[j].length; k++) {
                    if(this.grid[j][k] == text.charAt(i))
                    {
                        position[0][0]=j;
                        position[0][1]=k;
                    }
                    if(this.grid[j][k] == text.charAt(i+1))
                    {
                        position[1][0]=j;
                        position[1][1]=k;
                    }
                }
            }

            if(position[0][0]==position[1][0])
            {
                encryption+=this.grid[position[0][0]][(position[0][1]+1)%5];
                encryption+=this.grid[position[1][0]][(position[1][1]+1)%5];
            }
            else if(position[0][1]==position[1][1])
            {
                encryption+=this.grid[(position[0][0]+1)%5][position[0][1]];
                encryption+=this.grid[(position[1][0]+1)%5][position[1][1]];

            }
            else{
                encryption+=this.grid[position[0][0]][position[1][1]];
                encryption+=this.grid[position[1][0]][position[0][1]];
            }


        }

        return encryption;
    }


    public  String decrypt(String text)
    {
        String decryption="";

        text=text.replaceAll(" ","");
        text=text.toUpperCase();


        for (int i = 0; i < text.length(); i+=2) {
            int position[][]=new int[2][2];
            for (int j = 0; j < this.grid.length; j++) {
                for (int k = 0; k < this.grid[j].length; k++) {
                    if(this.grid[j][k] == text.charAt(i))
                    {
                        position[0][0]=j;
                        position[0][1]=k;
                    }
                    if(this.grid[j][k] == text.charAt(i+1))
                    {
                        position[1][0]=j;
                        position[1][1]=k;
                    }
                }
            }


            if(position[0][0]==position[1][0])
            {
                decryption+=this.grid[position[0][0]][position[0][1]-1<0?4:position[0][1]-1];
                decryption+=this.grid[position[1][0]][position[1][1]-1<0?4:position[1][1]-1];
            }
            else if(position[0][1]==position[1][1])
            {
                decryption+=this.grid[position[0][0]-1<0?4:position[0][0]-1][position[0][1]];
                decryption+=this.grid[position[1][0]-1<0?4:position[1][0]-1][position[1][1]];
            }
            else{
                decryption+=this.grid[position[0][0]][position[1][1]];
                decryption+=this.grid[position[1][0]][position[0][1]];
            }

        }


        return decryption;
    }


    public void printGrid()
    {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                if(j==this.grid[i].length-1)
                    System.out.println(this.grid[i][j]);
                else
                    System.out.print(this.grid[i][j]+" ");
            }
        }
    }

    public String getStringGrid()
    {
        String gridString="";
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                if(j==this.grid[i].length-1)
                    gridString+=this.grid[i][j]+"\n";
                else
                    gridString+=this.grid[i][j]+" ";

            }
        }
        return  gridString;
    }


    public void setGrid() {
        String fullKey=this.key+"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String finalKey="";
        for (int i = 0; i < fullKey.length(); i++) {
            if (finalKey.length() == 25)
                break;
            if(finalKey.indexOf(fullKey.charAt(i)) == -1 && fullKey.charAt(i) != this.letterSkip) {

                finalKey += fullKey.charAt(i);
            }

        }
        for (int i = 0,stringIndex=0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++,stringIndex++) {
                this.grid[i][j]=finalKey.charAt(stringIndex);
            }
        }


    }

    public void setLetterSkip(char letterSkip) {
        this.letterSkip = letterSkip;
    }

    public char getLetterSkip() {
        return letterSkip;
    }
}
