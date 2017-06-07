/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.solr.common.cloud;

import java.util.SortedSet;

/**
 * Listener that can be used with {@link ZkStateReader#registerLiveNodesListener(LiveNodesListener)}
 * and called whenever the live nodes set changes.
 */
public interface LiveNodesListener {

  /**
   * Called when a change in the live nodes set occurs.
   *
   * Note that, due to the way Zookeeper watchers are implemented, a single call may be
   * the result of several state changes
   *
   * @param oldLiveNodes set of live nodes before the change
   * @param newLiveNodes set of live nodes after the change
   */
  void onChange(SortedSet<String> oldLiveNodes, SortedSet<String> newLiveNodes);
}
