package com.googleKeep.googleKeep1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.googleKeep.googleKeep1.Model.LabelModel;

@Repository
public interface LabelRepository extends JpaRepository<LabelModel, Integer> {

}
