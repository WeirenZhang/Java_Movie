package com.weiren.zhang.library_network.observer;

import java.util.List;

public interface NetCallback<T extends List> {
    void success(T response);

    void error(String msg);
}
