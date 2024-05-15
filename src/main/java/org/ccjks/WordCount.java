package org.ccjks;

import java.io.*;

public class WordCount {


    public static void main(String[] args) throws IOException {

        if (args ==null){
            return;
        }
        String command="";
        String textTobePrinted="";

        InputStream inputStream=null;
        File file=null;



        try {
            if (System.console()==null && System.in.available()>0){
                //System.out.println("piped input");
                command=args[0];
                inputStream = System.in;

            }
            else{
                //System.out.println("file path provided");
                if (args.length==2){
                    command=args[0];
                }
                String filePath=args[args.length-1];;
                file=new File(filePath);
                inputStream = new FileInputStream(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



            try (BufferedInputStream inputBuffer = new BufferedInputStream(inputStream)) {

                String fileContent = new String((inputBuffer.readAllBytes()));
                int lines=fileContent.split("/n").length;
                int words=fileContent.split(" ").length;
                int chars=fileContent.split("").length;
                textTobePrinted = switch (command) {
                    case "-l" -> String.valueOf(lines);
                    case "-w" -> String.valueOf(words);
                    case "-m" -> String.valueOf(chars);
                    case "" -> String.valueOf(lines) + " " + String.valueOf(words) + " " + String.valueOf(chars);
                    default -> textTobePrinted;
                };
                if (file !=null){
                    textTobePrinted+=" "+file;
                }
                System.out.println(textTobePrinted);

            }
         catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
