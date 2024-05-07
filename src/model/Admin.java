package model;


public final class Admin extends Usuario{

    public Admin(String nome, String endereco, String email, String telefone, String cpf, boolean admin) {
        super(nome, endereco, email, telefone, cpf, admin);
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
        "////////////////////////////\n";
    }

}

