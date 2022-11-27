package com.team.serveur_app.utils;

import com.team.serveur_app.model.plat.Plat;

import java.io.IOException;

public interface PlatOnClickListener {
    void onPlatClick(Plat plat) throws IOException;
}
