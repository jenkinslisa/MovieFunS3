package org.superbiz.moviefun;


import java.io.IOException;
import java.util.Optional;

/**
 * Created by lisajenkins on 5/7/17.
 */

public interface BlobStore {

    void put(Blob blob) throws IOException;

    Optional<Blob> get(String name) throws IOException;

    void deleteAll();
}