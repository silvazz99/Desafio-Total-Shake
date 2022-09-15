package br.com.desafio.totalshake.Domain.produto;

import br.com.desafio.totalshake.Domain.ingredientes.Adicional;
import br.com.desafio.totalshake.Domain.ingredientes.Base;
import br.com.desafio.totalshake.Domain.ingredientes.Fruta;
import br.com.desafio.totalshake.Domain.ingredientes.Topping;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shake {
    private Base base;
    private Fruta fruta;
    private Topping topping;
    private List<Adicional> adicionais;
    private TipoTamanho  tipoTamanho;

    public <T> Shake(Base base, Fruta fruta, Topping topping, TipoTamanho tipoTam) {
        this.base = base;
        this.fruta = fruta;
        this.topping = topping;
        this.adicionais = new ArrayList<>(0);
        this.tipoTamanho = tipoTam;
    }

    public Shake(Base base, Fruta fruta, Topping topping, ArrayList<Adicional> adicionais, TipoTamanho tipoTam) {
        this.base = base;
        this.fruta = fruta;
        this.topping = topping;
        this.adicionais = adicionais;
        Collections.sort(this.adicionais, Comparator.comparing(a -> a.obterTipo().toString()));

        this.tipoTamanho = tipoTam;
    }

    public Base getBase() {
        return base;
    }

    public Fruta getFruta() {
        return fruta;
    }

    public Topping getTopping() {
        return topping;
    }

    public List<Adicional> getAdicionais() {
        return adicionais;
    }

    public TipoTamanho getTipoTamanho() {
        return tipoTamanho;
    }

    @Override
    public String toString() {
        return this.base.getTipoBase().toString() + " / " + this.fruta.getTipoFruta().toString() + " / " + this.topping.getTipoTopping().toString() + " / " + this.adicionais + " / " + this.tipoTamanho.toString();
    }
}
