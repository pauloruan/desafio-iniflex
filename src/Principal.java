import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {
	public static void main(String[] args) {
		List<Funcionario> funcionarios = new ArrayList<>();

		// 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
		funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
		funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));
		
		// 3.2 - Remover o funcionário "João" da lista.
		funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
		System.out.println("Funcionário \"João\" removido.");
		
		// 3.3 – Imprimir todos os funcionários com todas suas informações.
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat currencyFormatter = new DecimalFormat("#,##0.00");
        
        for (Funcionario funcionario : funcionarios) {
            String dataNascimentoFormatted = funcionario.getDataNascimento().format(dateFormatter);
            String salarioFormatted = currencyFormatter.format(funcionario.getSalario());

            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + dataNascimentoFormatted);
            System.out.println("Salário: " + salarioFormatted);
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }
        
        // 3.4 - Aumentar o salário dos funcionários em 10%.
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.valueOf(1.10));
            funcionario.setSalario(novoSalario);
        }
        
        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }
        
        // 3.6 - Imprimir os funcionários agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            String funcao = entry.getKey();
            List<Funcionario> funcionariosDaFuncao = entry.getValue();

            System.out.println("Função: " + funcao);
            for (Funcionario funcionario : funcionariosDaFuncao) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Salário: " + currencyFormatter.format(funcionario.getSalario()));
            }
            System.out.println();
        }
        
        // 3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("Funcionários que fazem aniversário no mês 10 e 12:");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter));
            }
        }
        System.out.println();
        
        // 3.9 - Imprimir o funcionário com a maior idade
        LocalDate hoje = LocalDate.now();
        int maiorIdade = -1;
        Funcionario funcionarioMaisVelho = null;

        for (Funcionario funcionario : funcionarios) {
            int idade = hoje.getYear() - funcionario.getDataNascimento().getYear();
            if (idade > maiorIdade) {
                maiorIdade = idade;
                funcionarioMaisVelho = funcionario;
            }
        }

        if (funcionarioMaisVelho != null) {
            int idade = maiorIdade;
            System.out.println("Funcionário mais velho:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + idade);
            System.out.println();
        }
        
        // 3.10 - Imprimir a lista de funcionários em ordem alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));

        System.out.println("Lista de funcionários em ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter));
            System.out.println("Salário: " + currencyFormatter.format(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }
        System.out.println();
        
	}
}