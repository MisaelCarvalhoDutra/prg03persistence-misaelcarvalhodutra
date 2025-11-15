/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.ifba;

//aqui são as importações de cada interface gráfica relacionada
import br.com.ifba.curso.view.CursoAdicionar;
import br.com.ifba.curso.view.CursoEditar;
import br.com.ifba.curso.view.CursoListar;

/**
 *
 * @author misae
 */
public class CursoSave {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            
            //aqui cria e exibe a tela principal
        new CursoListar().setVisible(true);
    });
    }
}

