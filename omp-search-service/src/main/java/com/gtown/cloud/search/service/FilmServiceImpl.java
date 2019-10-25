package com.gtown.cloud.search.service;

import com.gtown.cloud.search.entity.Film;
import com.gtown.cloud.search.interfaces.IFilmService;
import com.gtown.cloud.search.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author penn
 * @since 2019/10/23
 */
@Service
public class FilmServiceImpl implements IFilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }
}
