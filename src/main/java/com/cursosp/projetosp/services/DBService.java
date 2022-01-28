package com.cursosp.projetosp.services;

import com.cursosp.projetosp.domain.Categoria;
import com.cursosp.projetosp.domain.Cidade;
import com.cursosp.projetosp.domain.Cliente;
import com.cursosp.projetosp.domain.Endereco;
import com.cursosp.projetosp.domain.Estado;
import com.cursosp.projetosp.domain.ItemPedido;
import com.cursosp.projetosp.domain.Pagamento;
import com.cursosp.projetosp.domain.PagamentoComBoleto;
import com.cursosp.projetosp.domain.PagamentoComCartao;
import com.cursosp.projetosp.domain.Pedido;
import com.cursosp.projetosp.domain.Produto;
import com.cursosp.projetosp.enums.EstadoPagamento;
import com.cursosp.projetosp.enums.Perfil;
import com.cursosp.projetosp.enums.TipoCliente;
import com.cursosp.projetosp.repositories.CategoriaRepository;
import com.cursosp.projetosp.repositories.CidadeRepository;
import com.cursosp.projetosp.repositories.ClienteRepository;
import com.cursosp.projetosp.repositories.EnderecoRepository;
import com.cursosp.projetosp.repositories.EstadoRepository;
import com.cursosp.projetosp.repositories.ItemPedidoRepository;
import com.cursosp.projetosp.repositories.PagamentoRepository;
import com.cursosp.projetosp.repositories.PedidoRepository;
import com.cursosp.projetosp.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private BCryptPasswordEncoder password;

    public void instantiateTestDatabase() throws ParseException {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null, "Computador", 3000.0);
        Produto p2 = new Produto(null, "Impressora", 800.0);
        Produto p3 = new Produto(null, "Mouse", 80.0);
        Produto p4 = new Produto(null, "Mesa de escritório", 300.0);
        Produto p5 = new Produto(null, "Toalha", 50.0);
        Produto p6 = new Produto(null, "Colcha", 200.0);
        Produto p7 = new Produto(null, "TV true color", 1200.0);
        Produto p8 = new Produto(null, "Roçadeira", 800.0);
        Produto p9 = new Produto(null, "Abajour", 100.0);
        Produto p10 = new Produto(null, "Pendente", 180.0);
        Produto p11 = new Produto(null, "Shampoo", 90.0);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est1);

        est1.getCidades().addAll(Arrays.asList(c1, c3));
        est2.getCidades().addAll(Arrays.asList(c2));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "mariaareboco@gmail.com", "45412136325", TipoCliente.PESSOAFISICA, password.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("47965231452", "7932568547"));
        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "89025258", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "89014523", cli1, c2);
        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        Cliente cli2 = new Cliente(null, "João Marques", "joao@yahoo.com", "0668643938", TipoCliente.PESSOAFISICA, password.encode("123"));
        cli2.addPerfil(Perfil.ADMIN);
        cli2.getTelefones().addAll(List.of("478521459856"));
        cli2.getEnderecos().addAll(List.of(e2));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf1.parse("30/09/2022 10:32"), cli1, e1);
        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pedido ped2 = new Pedido(null, sdf1.parse("10/10/2022 10:32"), cli1, e2);
        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf1.parse("20/10/2022 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);

        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.0);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(List.of(ip3));

        p1.getItens().addAll(List.of(ip1));
        p2.getItens().addAll(List.of(ip3));
        p3.getItens().addAll(List.of(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
