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
package com.alipay.sofa.rpc.transmit;

import com.alipay.sofa.rpc.common.utils.ExceptionUtils;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.ext.ExtensionClass;
import com.alipay.sofa.rpc.ext.ExtensionLoader;
import com.alipay.sofa.rpc.ext.ExtensionLoaderFactory;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public class TransmitLauncherFactory {

    /**
     * Extension加载器
     */
    private final static ExtensionLoader<TransmitLauncher> EXTENSION_LOADER = ExtensionLoaderFactory
                                                                                .getExtensionLoader(TransmitLauncher.class);

    /**
     * 初始化TransmitLauncher实例
     *
     * @param level 转发级别
     * @return TransmitLauncher实例
     */
    public static TransmitLauncher getTransmitLauncher(String level) {
        try {
            ExtensionClass<TransmitLauncher> extensionClass = EXTENSION_LOADER.getExtensionClass(level);

            if (extensionClass == null) {
                throw ExceptionUtils.buildRuntime("transmit.level", level);
            }
            return extensionClass.getExtInstance();
        } catch (SofaRpcRuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new SofaRpcRuntimeException(e.getMessage(), e);
        }
    }

}