package edu.escuelaing.edu;

import static spark.Spark.*;

public class CollatzService {
    public static void main(String[] args) {
        port(5001);
        get("/",(req,res)->{return webClientHtml();});
        get("/collatzsequence", (req,res) -> {
            res.type("application/json");
            return sequenceBuilder(req.queryParams("value"));
        });
    }

    public static String sequenceBuilder(String numberString){
        String input = "";
        String out = "";
        Integer number = 0;
        try {
            number = Integer.parseInt(numberString);
            input = numberString;
        }
        catch (Exception e){
            return "Solo ingrese numeros por favor";
        }
        StringBuilder sequence = new StringBuilder("" + number);
        while (number != 1){
            number = collatz(number);
            sequence.append("->").append(number);

        }
        out = sequence.toString();
        return "{\"operation\":\"collatzsequence\",\"input\":\""+input+"\",\"output\":\""+out+"\"}";
    }

    public static int collatz(int n){
        if (n%2 == 0){
            return n/2;
        }
        else{
            return 3*n + 1;
        }
    }

    public static String webClientHtml(){
        return "<html>\n" +
                "    <head>\n" +
                "        <title>Collatz sequence</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Collatz sequence</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">number:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"1\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let number = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/collatzsequence?value=\"+number);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "    </body>\n" +
                "</html>";
    }

}
