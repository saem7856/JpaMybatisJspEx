package com.sp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.app.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
