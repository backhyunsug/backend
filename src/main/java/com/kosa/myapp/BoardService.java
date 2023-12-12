package com.kosa.myapp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService
{
    private final BoardRepository repository;
    Page<Board> getList(String pg){
//        Pageable sortedByName =
//                PageRequest.of(Integer.parseInt(pg), 3, Sort.by("name"));

        Pageable page =
                PageRequest.of(Integer.parseInt(pg), 3);
        return repository.findAll(page);
    }

    void insert(Board board){
        repository.save(board);
    }
    Long getTotalCount(){
        return repository.count();
    }
}
