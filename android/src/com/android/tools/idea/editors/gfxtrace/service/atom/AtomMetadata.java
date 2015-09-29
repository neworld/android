/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * THIS FILE WAS GENERATED BY codergen. EDIT WITH CARE.
 */
package com.android.tools.idea.editors.gfxtrace.service.atom;

import com.android.tools.rpclib.schema.Entity;
import com.android.tools.rpclib.schema.Field;
import com.android.tools.rpclib.schema.Struct;
import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;

import com.android.tools.rpclib.binary.BinaryClass;
import com.android.tools.rpclib.binary.BinaryID;
import com.android.tools.rpclib.binary.BinaryObject;
import com.android.tools.rpclib.binary.Decoder;
import com.android.tools.rpclib.binary.Encoder;
import com.android.tools.rpclib.binary.Namespace;

import java.io.IOException;

public final class AtomMetadata implements BinaryObject {
  public static AtomMetadata find(Entity c) {
    for (BinaryObject o : c.getMetadata()) {
      if (o instanceof AtomMetadata) {
        AtomMetadata meta = (AtomMetadata)o;
        meta.prepare(c);
        return meta;
      }
    }
    return null;
  }

  boolean myIsPrepared = false;
  int myResultIndex = -1;
  int myObservationsIndex = -1;
  @NotNull private static final Logger LOG = Logger.getInstance(AtomMetadata.class);

  private void prepare(Entity c) {
    if (myIsPrepared) return;
    myIsPrepared = true;
    for (int index = 0; index < c.getFields().length; index++) {
      Field field = c.getFields()[index];
      if (field.getDeclared().equals("Result")) {
        myResultIndex = index;
      }
      if (field.getType() instanceof Struct) {
        BinaryID id = ((Struct)field.getType()).getID();
        if (id.equals(Observations.ID)) {
          myObservationsIndex = index;
        }
      }
    }
  }

  //<<<Start:Java.ClassBody:1>>>
  private BinaryID myAPI;
  private String myDisplayName;
  private boolean myEndOfFrame;
  private boolean myDrawCall;
  private String myDocumentationUrl;

  // Constructs a default-initialized {@link AtomMetadata}.
  public AtomMetadata() {}


  public BinaryID getAPI() {
    return myAPI;
  }

  public AtomMetadata setAPI(BinaryID v) {
    myAPI = v;
    return this;
  }

  public String getDisplayName() {
    return myDisplayName;
  }

  public AtomMetadata setDisplayName(String v) {
    myDisplayName = v;
    return this;
  }

  public boolean getEndOfFrame() {
    return myEndOfFrame;
  }

  public AtomMetadata setEndOfFrame(boolean v) {
    myEndOfFrame = v;
    return this;
  }

  public boolean getDrawCall() {
    return myDrawCall;
  }

  public AtomMetadata setDrawCall(boolean v) {
    myDrawCall = v;
    return this;
  }

  public String getDocumentationUrl() {
    return myDocumentationUrl;
  }

  public AtomMetadata setDocumentationUrl(String v) {
    myDocumentationUrl = v;
    return this;
  }

  @Override @NotNull
  public BinaryClass klass() { return Klass.INSTANCE; }

  private static final byte[] IDBytes = {19, 125, 65, -49, -11, 97, 15, 37, -111, -36, -30, 79, -13, 35, -46, -27, -58, -3, -89, -91, };
  public static final BinaryID ID = new BinaryID(IDBytes);

  static {
    Namespace.register(ID, Klass.INSTANCE);
  }
  public static void register() {}
  //<<<End:Java.ClassBody:1>>>
  public enum Klass implements BinaryClass {
    //<<<Start:Java.KlassBody:2>>>
    INSTANCE;

    @Override @NotNull
    public BinaryID id() { return ID; }

    @Override @NotNull
    public BinaryObject create() { return new AtomMetadata(); }

    @Override
    public void encode(@NotNull Encoder e, BinaryObject obj) throws IOException {
      AtomMetadata o = (AtomMetadata)obj;
      e.id(o.myAPI);
      e.string(o.myDisplayName);
      e.bool(o.myEndOfFrame);
      e.bool(o.myDrawCall);
      e.string(o.myDocumentationUrl);
    }

    @Override
    public void decode(@NotNull Decoder d, BinaryObject obj) throws IOException {
      AtomMetadata o = (AtomMetadata)obj;
      o.myAPI = d.id();
      o.myDisplayName = d.string();
      o.myEndOfFrame = d.bool();
      o.myDrawCall = d.bool();
      o.myDocumentationUrl = d.string();
    }
    //<<<End:Java.KlassBody:2>>>
  }
}
