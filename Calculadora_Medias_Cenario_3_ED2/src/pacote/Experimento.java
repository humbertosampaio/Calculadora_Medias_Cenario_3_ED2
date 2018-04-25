package pacote;

import java.util.LinkedList;
import java.util.List;

public class Experimento
{
	public String id;
	public String N;
	public List<Iteracao> iteracoes;
	
	public Experimento()
	{
		iteracoes = new LinkedList<Iteracao>();
	}
}
