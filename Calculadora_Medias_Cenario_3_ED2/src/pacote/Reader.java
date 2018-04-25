package pacote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

public class Reader
{
	public void read(File file)
	{
		System.out.println("Obtendo registros do arquivo \"" + file.getName() + "\"...");
		
		List<Experimento> experimentos = new LinkedList<Experimento>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String linha;
			
			while ((linha = reader.readLine()) != null)
			{
				if(linha.contains("Experimento"))
				{
					Experimento experimento = new Experimento();
					experimento.id = String.valueOf(linha.charAt(linha.indexOf(':') + 2));
					experimento.N = getValue(linha, "=");
					
					for (int i = 0; i < 5; i++)
					{
						Iteracao iteracao = new Iteracao();
						
						while (!(linha = reader.readLine()).contains("Iteracao"))
						{
							
						}
						
						iteracao.id = getValue(linha, "o");
						
						linha = reader.readLine();
						iteracao.seed = getValue(linha);
						
						linha = reader.readLine();
						iteracao.algoritmo = getValue(linha);
						
						linha = reader.readLine();
						linha = reader.readLine();
						iteracao.comparacoes = Integer.parseInt(getValue(linha));
						
						linha = reader.readLine();
						iteracao.copias = Integer.parseInt(getValue(linha));
						
						linha = reader.readLine();
						iteracao.tempoExecucao = Float.parseFloat(getValue(linha));

						experimento.iteracoes.add(iteracao);
					}
					
					experimentos.add(experimento);
				}
			}
			
			NumberFormat df = new DecimalFormat("#0.00000000");
			for (int i = 0; i < 6; i++)
			{
				for(int j = 0; j < 5; j++)
				{
					Experimento e = experimentos.get(j + (i * 5));
					System.out.println("### " + e.iteracoes.get(0).algoritmo + ", N = " + e.N + " ###");
					
					double comparacoes = 0, copias = 0, tempoExecucao = 0;
					for(Iteracao it : e.iteracoes)
					{
						comparacoes += it.comparacoes / 5d;
						copias += it.copias / 5d;
						tempoExecucao += it.tempoExecucao / 5d;
					}
					System.out.println("Media de comparacoes: " + df.format(comparacoes));
					System.out.println("Media de copias: " + df.format(copias));
					System.out.println("Media de tempo de execucao: " + df.format(tempoExecucao));
					System.out.println();
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getValue(String linha)
	{
		return getValue(linha, ":");
	}
	
	public String getValue(String linha, String referencia)
	{
		return linha.substring(linha.indexOf(referencia) + 2, linha.length());
	}
}
