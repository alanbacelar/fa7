package br.com.trabalho.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;

@ManagedBean
@SessionScoped
public class JogoForcaBean {
	private List<String> letras;
	private List<String> palpites;
	private List<String> letrasCertas;
	
	private List<String> palavras;
	private List<String> dicas;
	
	private int position;
	private int chances;
	
	private String mensagem = "";
	
	public JogoForcaBean() {
		newGame();
	}
	
	private UIInput palpiteInput;

	public UIInput getPalpiteInput() {
		return palpiteInput;
	}

	public void setPalpiteInput(UIInput palpiteInput) {
		this.palpiteInput = palpiteInput;
	}

	public List<String> getLetras() {
		return letras;
	}
	
	public String getDica() {
		return dicas.get(position);
	}
	
	public void onChange() {
		String palpite = palpiteInput.getValue().toString().toUpperCase();

		if (!palpite.isEmpty()) {
			palpites.add(palpite);
			
			if (letras.contains(palpite) && !letrasCertas.contains(palpite)) {
				letrasCertas.add(palpite);
				mensagem = "page.forca.acerto";
			} else {
				mensagem = "page.forca.erro";
			}
		}
		
		if (fimDeJogo()) {
			mensagem = getMessagemFimJogo();
		}
		
		this.palpiteInput.setValue("");
	}
	
	public boolean mostrarLetra(String letra) {
		return letrasCertas.contains(letra);
	}
	
	public List<String> getPalpites() {
		return palpites;
	}
	
	public int getChances() {
		return (chances - qtdErrors());
	}
	
	public boolean temChances() {
		return (qtdErrors() < chances);
	}
	
	public int qtdErrors() {
		return (palpites.size() - letrasCertas.size());
	}
	
	public void newGame() {
		palpites = new ArrayList<String>();
		letrasCertas = new ArrayList<String>();
		chances = 6;
		mensagem = "";
		
		palavras = new ArrayList<String>();

		palavras.add("BOLA");
		palavras.add("FUTEBOL");
		palavras.add("JAVA");
		palavras.add("CACHORRO");
		palavras.add("GATO");
		palavras.add("CASA");
		palavras.add("FLAMENGO");
		palavras.add("BRASIL");
		palavras.add("INBOX");
		palavras.add("ANGULARJS");
		
		dicas = new ArrayList<String>();

		dicas.add("Objeto circular");
		dicas.add("Maior esporte do mundo");
		dicas.add("Linguagem de programação");
		dicas.add("Melhor amigo do homem");
		dicas.add("Felino doméstico");
		dicas.add("Lar");
		dicas.add("Clube de maior torcida do mundo");
		dicas.add("Nosso país");
		dicas.add("Novo aplicativo de e-mail da Google");
		dicas.add("Framework Javascript mantido pela Google");
		
		geraPalavra();
	}
	
	public boolean acertou() {
		for(String letra: letras) {
			if (!letrasCertas.contains(letra)) {
				return false;
			}
		}

		return true;
	}
	
	public boolean fimDeJogo() {
		return acertou() || !temChances();
	}
	
	public String getMessagemFimJogo() {
		if (acertou()) {
			return "page.forca.fim.acerto";
		}
		
		return "page.forca.fim.erro";
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public boolean hasMensagem() {
		return !mensagem.isEmpty();
	}
	
	public void geraPalavra() {
		position = (int) (Math.random() * palavras.size());
		System.out.println("POSITION: "+position);
		
		String[] palavra = palavras.get(position).split("");
		this.letras = new ArrayList<String>();

		for(String letra: palavra) {
			this.letras.add(letra);
		}
 	}

}