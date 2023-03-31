package test;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler{
    DictionaryManager dm;
    PrintWriter out;
    Scanner in;
    
    @Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient)
    {
        out = new PrintWriter(outToClient);
        in = new Scanner(inFromClient);
        dm = DictionaryManager.get();
        
        String[] inputFromClient = in.next().split(",");
        boolean isWordExists = false;

        if(inputFromClient.equals('Q'))
        {
            isWordExists = dm.query(Arrays.copyOfRange(inputFromClient, 1, inputFromClient.length));
        }
        else
        {//'C'
            //Deletes the 'C' from the array, calling challenge with just the files names and the word to check
            isWordExists = dm.challenge(Arrays.copyOfRange(inputFromClient, 1, inputFromClient.length));
        }

        out.println(isWordExists ? "true" : "false");
        out.flush();
    }
    
    @Override
    public void close()
    {
        out.close();
        in.close();
    }
}
