package com.dim.storedint.file.hash;

import com.dim.storedint.PojaGenerated;

@PojaGenerated
public record FileHash(FileHashAlgorithm algorithm, String value) {}
