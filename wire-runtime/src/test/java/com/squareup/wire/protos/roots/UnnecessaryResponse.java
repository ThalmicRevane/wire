// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: ../wire-runtime/src/test/proto/service_root.proto
package com.squareup.wire.protos.roots;

import com.squareup.wire.Message;
import java.lang.Object;
import java.lang.Override;

public final class UnnecessaryResponse extends Message {
  private static final long serialVersionUID = 0L;

  public UnnecessaryResponse() {
  }

  private UnnecessaryResponse(Builder builder) {
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof UnnecessaryResponse;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int size() {
    int size = this.size;
    if (size == -1) {
      size = unknownFieldsSize();
    }
    this.size = size;
    return size;
  }

  public static final class Builder extends com.squareup.wire.Message.Builder<UnnecessaryResponse> {
    public Builder() {
    }

    public Builder(UnnecessaryResponse message) {
      super(message);
    }

    @Override
    public UnnecessaryResponse build() {
      return new UnnecessaryResponse(this);
    }
  }
}
