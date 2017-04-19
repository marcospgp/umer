public class Motorista extends Utilizador{
    
    public Motorista(String newEmail, String newNome, String newPassword, String newMorada, String newDataNascimento){
                  super(newEmail, newNome, newPassword, newMorada, newDataNascimento);
    }
    // grau de cumprimento de horário estabelecido com o cliente, dado por um factor entre 0 e 100
    public int cumprimento;
    // classificação do motorista, dado numa escala de 0 a 100, calculada com base na classificação dada pelo cliente no final da viagem;
    public int classificacao;
    // historico de viagens array?
    
    // kms feitos na empresa
    public int kms;
    // informacao se está disponivel ou não, a trabalhar ou não
    public boolean disponibilidade;
} 