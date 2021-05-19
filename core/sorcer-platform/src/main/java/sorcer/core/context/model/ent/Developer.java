/*
 * Copyright 2021 the original author or authors.
 * Copyright 2021 SorcerSoft.org.
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
 */

package sorcer.core.context.model.ent;

import sorcer.core.signature.LocalSignature;
import sorcer.service.*;
import sorcer.service.modeling.ExecutiveException;
import sorcer.service.modeling.Functionality;

import java.rmi.RemoteException;

/**
 * Created by Mike Sobolewski on 05/12/21.
 */
public class Developer extends Entry<Development> implements Controller, Development {

    private static final long serialVersionUID = 1L;

    private Design design;

    private Signature signature;

    public Developer(String name, Development development)  {
        this.key = name;
        this.impl = development;
        this.type = Functionality.Type.DEVELOPER;
    }

    public Developer(String name, Signature signature) {
        this.key = name;
        this.signature = signature;
        this.type = Functionality.Type.DEVELOPER;
    }

    public Developer(String name, Development develper, Context context) {
        this.key = name;
        scope = context;
        this.impl = develper;
        this.type = Functionality.Type.DEVELOPER;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }

    public Development getDeveloper() {
        return (Development) impl;
    }

    public void setDeveloper(Development developer) {
        impl = developer;
    }

    public Signature getSignature() {
        return signature;
    }

    @Override
    public Context develop(Design design, Context context) throws ServiceException, ExecutiveException, RemoteException {
        this.design = design;
        Context out = null;
        try {
            if (impl != null && impl instanceof Development) {
                if (this.design == null) {
                    out = ((Development) impl).develop(design, context);
                } else {
                    out = ((Development) impl).develop(design, context);
                }
            } else if (signature != null) {
                impl = ((LocalSignature) signature).initInstance();
                out = ((Development) impl).develop(design, context);
            } else if (impl == null) {
                throw new InvocationException("No developer available!");
            }
        } catch (ContextException | SignatureException | ExecutiveException |  RemoteException e) {
            throw new ServiceException(e);
        }
        return out;
    }
}
