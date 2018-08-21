package com.example.konikiewiczb.myapplication.framework;

import java.util.List;

public interface Dataset {
    void setDataset(List dataset);
    void notifyDataSetChanged();
}
