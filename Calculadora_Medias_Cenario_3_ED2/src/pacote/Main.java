package pacote;

import java.io.File;

public class Main
{

	public static void main(String[] args)
	{
		File file = new File("E:\\UFJF\\4o Periodo\\Estruturas de Dados 2\\testes_cenarios\\1.3-comparacoesAlgoritmosOrdenacao.txt");
		Reader r = new Reader();
		r.read(file);
	}

}
