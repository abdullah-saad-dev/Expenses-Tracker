package com.abdullah.ui.command;

import java.util.List;

import com.abdullah.model.Transaction;

public interface Command {
    void execute();

    default void printList(List<Transaction> list) {
        list.forEach(el -> System.out.println(el + " \n" +
                "----------------------------------------------"));
    }

}
