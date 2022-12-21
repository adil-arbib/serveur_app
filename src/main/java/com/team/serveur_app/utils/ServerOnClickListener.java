package com.team.serveur_app.utils;

import com.team.serveur_app.model.serveur.Serveur;

import java.io.IOException;

public interface ServerOnClickListener {
    void onServerClick(Serveur serveur) throws IOException;
}
