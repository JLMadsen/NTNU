fn replace(mut input: String) -> String
{
    let mut newString=String::new();

    newString = input.replace("&", "&amp;");
    newString = newString.replace("<", "&lt;");
    newString = newString.replace(">", "&gt;");

    newString
}

fn main()
{
    use std::io::{stdin,stdout,Write};
    let mut s=String::new();

    print!("Enter text: ");
    let _=stdout().flush();
    stdin().read_line(&mut s).expect("Did not enter a correct string");
    
    if let Some('\n')=s.chars().next_back() {
        s.pop();
    }
    if let Some('\r')=s.chars().next_back() {
        s.pop();
    }

    let mut output = replace(s);

    println!("output: {:?}",output);
}