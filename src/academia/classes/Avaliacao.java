package academia.classes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import academia.DAO.AlunoDAO;
import academia.DAO.AvaliacaoDAO;
import academia.DAO.PersonalTrainerDAO;
import academia.services.ValidacaoAvaliacao;

public class Avaliacao {
	private Aluno aluno;
	private LocalDate dataAvaliacao;
	private PersonalTrainer personalTrainer; 
	private String descricao;
	
	public Avaliacao(Aluno aluno, LocalDate dataAvaliacao, PersonalTrainer personalTrainer, String descricao) {
		this.aluno = aluno;
		this.dataAvaliacao = dataAvaliacao;
		this.personalTrainer = personalTrainer;
	 	this.descricao = descricao;
	}

	public LocalDate getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(LocalDate dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
	}
	
	public static void cadastrarAvaliacao() {
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Digite o seu CPF: ");
		String cpfPersonal = scanner.nextLine();
		System.out.println("Digite o CPF do Aluno: ");
		String cpfAluno = scanner.nextLine();
		
		PersonalTrainer personal = PersonalTrainerDAO.getDadosPersonal(cpfPersonal);
		Aluno aluno = AlunoDAO.getAluno(cpfAluno);
		
		LocalDate dataAvaliacao = ValidacaoAvaliacao.validaDataAvaliacao();
		System.out.println("Digite a descrição da Avaliação: ");
		String descricao = scanner.nextLine();
		
		AvaliacaoDAO.cadastrarAvaliacao(personal, aluno, dataAvaliacao, descricao);
	}
	
	public static String exibirAvaliacoes() throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		int dataInicio = ValidacaoAvaliacao.validaPeriodoAvaliacao("Digite o mês de inicio do periodo: (1-12)");
    	int dataFim = ValidacaoAvaliacao.validaPeriodoAvaliacao("Digite o mês de fim do periodo: (1-12)");
    	
    	System.out.println("Avaliações dos Meses entre: " + dataInicio + " e " + dataFim);
		return AvaliacaoDAO.exibirAvaliacaoPeriodo(dataInicio, dataFim);
	}
	@Override
	public String toString() {
		return "Nome do aluno: " + aluno + "\nData da Avaliação: " + dataAvaliacao + "\nPersonal Trainer: " + personalTrainer
				+ "\nDescrição: " + descricao ;
	}
	
	
}
