package br.senai.sp.jandira.controller;

import br.senai.sp.jandira.model.Conexao;
import br.senai.sp.jandira.model.Contato;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContatosController {

    Conexao conexao = new Conexao();

    Connection connection = conexao.getConexao();


    public String consultarContato() throws SQLException {
        Statement statement = connection.createStatement();
        String queryConsulta = "SELECT * FROM contatos";

        ResultSet resultSet = statement.executeQuery(queryConsulta);

        List<Contato> listContato = new ArrayList<>();

        while (resultSet.next()){

            Contato contato = new Contato();
            contato.setId(resultSet.getInt("id"));
            contato.setNome(resultSet.getString("nome"));
            contato.setEmail(resultSet.getString("email"));
            contato.setTelefone(resultSet.getLong("telefone"));
            contato.setFoto(resultSet.getString("foto"));
            contato.setFavorito(resultSet.getBoolean("favorito"));
            listContato.add(contato);

        }

        Gson gson = new Gson();
        String json = gson.toJson(listContato);
        return json;

    }




    public void pesquisarContato(){


    }

    public void deletarContato(String nome) throws SQLException {

        //Executa a Query no BANCO
        Statement statement = connection.createStatement();

        String queryDelete = " DELETE FROM contatos WHERE nome = '" + nome + "'";

        try {
            statement.executeUpdate(queryDelete);
            System.out.println("\nContato " + nome + " deletado!");
        }catch (Exception erro){
            System.out.println(erro);
        }



    }

    public void cadastrarContato(){


    }

}
