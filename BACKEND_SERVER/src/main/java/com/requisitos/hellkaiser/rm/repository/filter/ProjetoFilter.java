package com.requisitos.hellkaiser.rm.repository.filter;

import com.requisitos.hellkaiser.rm.model.Equipe;

import java.util.Collection;
import java.util.HashSet;

public class ProjetoFilter {
    public String nome = "";
    public int visivel = 0;
    public int concluido = 0;
    public Collection<Equipe> equipes = new HashSet<>();
}
