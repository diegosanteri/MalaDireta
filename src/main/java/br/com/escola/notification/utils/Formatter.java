package br.com.escola.notification.utils;

public class Formatter {
	
	public static String formatCpf( String cpf ) {
        while( cpf.length() < 11 ) {
            cpf = "0" + cpf;
        }
        return format( cpf, "###.###.###-##" );
    }
	
	
	
	private static String format( String value, String mask ) {
	    
        String data = "";      
        for ( int i = 0; i < value.length(); i++ )  {
            char c = value.charAt(i);
            if ( Character.isDigit( c ) ) { data += c; }
        }

        int maskLen = mask.length();
        int dataLen = data.length();

        for ( ; dataLen > 0 && maskLen > 0; ) {
            if ( mask.charAt( --maskLen ) == '#' ) { dataLen--; }
        }

        String response = "";
        for ( ; maskLen < mask.length(); maskLen++ ) {    
        	response += ( ( mask.charAt( maskLen ) == '#' ) ? data.charAt( dataLen++ ) : mask.charAt( maskLen ) );
        }    
        return response;
    }
	
	
}
