class Test{
    public static void main(String[] args) {
        int tall = 2;
        int teller = 0;
        boolean divided = false;
        while(teller < 1000){
            divided = false;
            for(int i = 2; i<tall; i++){
                if(tall % i == 0){
                    divided = true;
                    break;
                }
            }
            if(!divided){
                System.out.println(tall);
                teller++;
            }
            tall++;
        }
    }
}