package model;

import util.Util;

public abstract class Usuario{
    protected String nome, endereco, email, telefone, cpf;
    protected boolean admin;

    public Usuario(String nome, String endereco, String email, String telefone, String cpf, boolean admin) {
        this.nome = Util.formataString(nome);
        this.endereco = Util.formataString(endereco);
        this.email = Util.formataString(email);
        this.telefone = Util.formataTelefone(telefone);
        this.cpf = Util.formataCpf(cpf);
        this.admin = admin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public abstract String info(); 
 
}
