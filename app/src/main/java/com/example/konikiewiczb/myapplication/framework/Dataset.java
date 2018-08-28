package com.example.konikiewiczb.myapplication.framework;

import java.util.List;

public interface Dataset<E> {
    void setDataset(List<E> dataset);
    void notifyDataSetChanged();
}
