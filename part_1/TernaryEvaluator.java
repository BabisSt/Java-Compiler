  /**
    * exp-> term exp2
    * exp2-> + term exp2
            | - term exp2
            | e
    * term-> num term2
    * term2-> ** num term2
            | e
    
    * num-> digit num2
            | (exp)
    * num2-> digit num2
            | e
    * digit-> 0-9
    */

import java.io.InputStream;
import java.io.IOException;
import java.lang.Math;


class TernaryEvaluator{
    private final InputStream in;
    private int lookahead;

    public TernaryEvaluator(InputStream in) throws IOException
    {
        this.in =in;
        lookahead = in.read();
    }

    private void consume(int symbol) throws IOException,ParseError
    {
            if(lookahead == symbol)
                lookahead= in.read();
            else
                throw new ParseError();
    }

    private boolean isDigit(int c)
    {
        return '0' <= c && c <= '9';
    }

    private int evalDigit(int c)
    {
        return c -'0';
    }

    public int eval() throws IOException,ParseError
    {
        int value = Exp();

        if(lookahead != -1 && lookahead != '\n')
            throw new ParseError();
        
        return value;
    }

    private int Exp() throws IOException, ParseError
    {
        if(isDigit(lookahead))
        {
            int tmp = Term();           /* kanoume klhsh ths prwths sunarthshs kai meta auth thn timh thn bazoume sthn deuterh */
            return Exp_2(tmp);
            
        }
        
        else if(lookahead == '(')
        {
            int tmp = Term();           /* kanoume klhsh ths prwths sunarthshs kai meta auth thn timh thn bazoume sthn deuterh */
            return Exp_2(tmp);
        }

        throw new ParseError();

    }

    private int Exp_2(int condition) throws IOException, ParseError
    {

        switch(lookahead){
            case '+':
                consume(lookahead);
                int tmp_1 = Term();
                return condition + (Exp_2(tmp_1));
            
            case '-':
                consume(lookahead);
                int tmp_2 = Term();
                tmp_2 = -tmp_2;
                return condition + Exp_2(tmp_2);

            case ')':
                return condition;
            case -1:
            case '*':       //den eimai sigouros gia auto
            case '\n':
            case '(':
                return condition;
        }
        throw new ParseError();
    }

    private int Term() throws IOException, ParseError
    {
        if(isDigit(lookahead))
        {
            int tmp = Num();           /* kanoume klhsh ths prwths sunarthshs kai meta auth thn timh thn bazoume sthn deuterh */
            return Term_2(tmp);
        }
        else if (lookahead == '(')
        {
            int tmp = Num();           /* kanoume klhsh ths prwths sunarthshs kai meta auth thn timh thn bazoume sthn deuterh */
            return Term_2(tmp);
        }
        throw new ParseError();
    }

    private int Term_2(int condition) throws IOException, ParseError
    {
        switch(lookahead){
            case '*':
                consume('*');
                consume('*');
                int tmp = Num();           /* kanoume klhsh ths prwths sunarthshs kai meta auth thn timh thn bazoume sthn deuterh */
                return (int) Math.pow(condition, Term_2(tmp));
            case '+':
            case ')':
            case '-':
            case -1:
            case '\n':
                return condition;
        }
        throw new ParseError();
    }

    private int Num() throws IOException, ParseError
    {
        if(isDigit(lookahead))
        {
            int tmp = Digit();           /* kanoume klhsh ths prwths sunarthshs kai meta auth thn timh thn bazoume sthn deuterh */
            return Num_2(tmp);
        }
        else if (lookahead == '(')
        {
            consume(lookahead);
            int tmp = Exp();           /* kanoume klhsh ths prwths sunarthshs kai meta auth thn timh thn bazoume sthn deuterh */
            if(lookahead == ')')
                consume(lookahead);
            else
                throw new ParseError();
            return tmp;  
        }
        throw new ParseError();
    }

    private int Num_2(int condition) throws IOException, ParseError
    {
        if(isDigit(lookahead))
        {
            int tmp = Digit();           /* kanoume klhsh ths prwths sunarthshs kai meta auth thn timh thn bazoume sthn deuterh */
            int tmp_2 =  Concat(condition,tmp);
            return Num_2(tmp_2);
        }
        else{
            switch(lookahead){
                case '(':
                    consume('(');
                    int tmp_1 = Digit();           /* kanoume klhsh ths prwths sunarthshs kai meta auth thn timh thn bazoume sthn deuterh */
                    return condition + Num_2(tmp_1);
                case ')':
                case -1:
                case '+':
                case '*':
                case '-':
                case '\n':
                    return condition;
            }
        }
        throw new ParseError();
    }

    private int Digit() throws IOException, ParseError
    {
        if(isDigit(lookahead))
        {
            int cond = evalDigit(lookahead);
            consume(lookahead);
            return cond;
        }

        throw new ParseError();
    }

    static int Concat(int a, int b)
    {
        String s1 = Integer.toString(a);
        String s2 = Integer.toString(b);

        String s = s1 + s2;
        int c =  Integer.parseInt(s);

        return c;
    }

}