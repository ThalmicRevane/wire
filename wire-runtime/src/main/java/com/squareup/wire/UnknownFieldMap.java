/*
 * Copyright 2013 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.squareup.wire.ProtoWriter.makeTag;

final class UnknownFieldMap {

  static final class Value<T> {
    final int tag;
    final T value;
    final TypeAdapter<T> adapter;

    public Value(int tag, T value, TypeAdapter<T> adapter) {
      this.tag = tag;
      this.value = value;
      this.adapter = adapter;
    }

    int getSerializedSize() {
      return adapter.serializedSize(value);
    }

    void write(int tag, ProtoWriter output) throws IOException {
      output.writeVarint32(makeTag(tag, adapter.type));
      output.write(adapter, value);
    }
  }

  final Map<Integer, List<Value>> fieldMap;

  UnknownFieldMap() {
    this(null);
  }

  UnknownFieldMap(UnknownFieldMap other) {
    if (other != null && other.fieldMap != null) {
      fieldMap = new TreeMap<Integer, List<Value>>(other.fieldMap);
    } else {
      fieldMap = new TreeMap<Integer, List<Value>>();
    }
  }

  <T> void add(int tag, T value, TypeAdapter<T> adapter) throws IOException {
    addElement(tag, new Value<T>(tag, value, adapter));
  }

  /**
   * @throws IOException if the added element's type doesn't match the types of the other elements
   *     with the same tag.
   */
  private void addElement(int tag, Value value) throws IOException {
    List<Value> values = fieldMap.get(tag);
    if (values == null) {
      values = new ArrayList<Value>();
      fieldMap.put(tag, values);
    } else if (values.get(0).adapter.type != value.adapter.type) {
      throw new ProtocolException(
          String.format("Wire type %s differs from previous type %s for tag %s",
              value.adapter.type, values.get(0).adapter.type, tag));
    }
    values.add(value);
  }

  int getSerializedSize() {
    int size = 0;
    for (Map.Entry<Integer, List<Value>> entry : fieldMap.entrySet()) {
      int tagSize = ProtoWriter.tagSize(entry.getKey());
      for (Value value : entry.getValue()) {
        size += tagSize + value.getSerializedSize();
      }
    }
    return size;
  }

  void write(ProtoWriter output) throws IOException {
    for (Map.Entry<Integer, List<Value>> entry : fieldMap.entrySet()) {
      int tag = entry.getKey();
      for (Value value : entry.getValue()) {
        value.write(tag, output);
      }
    }
  }
}
