package model;


import java.util.List;

public class Cliente extends Usuario {
    private List<Livro> livros;

    public Cliente(String nome, String endereco, String email, String telefone, List<Livro> livros, String cpf, boolean admin) {
        super(nome, endereco, email, telefone, cpf, admin);
        this.livros = livros;
    } 

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String info(){
        return 
        "Nome: " + nome + "\n" +
        "Endereço: " + endereco + "\n" +
        "Telefone: " + telefone + "\n" +
        "Email: " + email + "\n" +
        "CPF: " + cpf + "\n" +
        "Tipo: " + ((admin)? "Admin":"Cliente") + "\n" + 
        "Livros: " + livros + "\n" +
        "////////////////////////////\n";
    }
}
