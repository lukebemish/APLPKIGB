/*
 * This file is part of APLP: KIGB, licensed under the MIT License
 *
 * Copyright (c) 2022 TheSilkMiner
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.thesilkminer.mc.austin.mappings

import groovy.transform.CompileStatic

@CompileStatic
class MappingMetaClass extends DelegatingMetaClass {

    final LoadedMappings mappings

    MappingMetaClass(MetaClass delegate, LoadedMappings mappings) {
        super(delegate)
        this.mappings = mappings
    }

    @Override
    Object invokeStaticMethod(Object object, String methodName, Object[] arguments) {
        try {
            return super.invokeStaticMethod(object, methodName, arguments)
        } catch (MissingMethodException e) {
            var map = mappings.methods.get(theClass.name)
            if (map!=null) {
                // Check whether the method is in the mappables
                // If it is, map it and invoke that method
                List<String> mapped = map.get(methodName)
                if (mapped!=null) for (String possible : mapped) {
                    try {
                        return super.invokeStaticMethod(object, possible, arguments)
                    } catch (MissingMethodException ignored) {}
                }
            }
            throw e
        }
    }

    @Override
    Object invokeMethod(Object object, String methodName, Object arguments) {
        try {
            return super.invokeMethod(object, methodName, arguments)
        } catch (MissingMethodException e) {
            var map = mappings.methods.get(theClass.name)
            if (map!=null) {
                // Check whether the method is in the mappables
                // If it is, map it and invoke that method
                List<String> mapped = map.get(methodName)
                if (mapped!=null) for (String possible : mapped) {
                    try {
                        return super.invokeMethod(object, possible, arguments)
                    } catch (MissingMethodException ignored) {}
                }
            }
            throw e
        }
    }

    @Override
    Object invokeMethod(Object object, String methodName, Object[] arguments) {
        try {
            return super.invokeMethod(object, methodName, arguments)
        } catch (MissingMethodException e) {
            var map = mappings.methods.get(theClass.name)
            if (map!=null) {
                // Check whether the method is in the mappables
                // If it is, map it and invoke that method
                List<String> mapped = map.get(methodName)
                if (mapped!=null) for (String possible : mapped) {
                    try {
                        return super.invokeMethod(object, possible, arguments)
                    } catch (MissingMethodException ignored) {}
                }
            }
            throw e
        }
    }

    @Override
    Object invokeMethod(String name, Object args) {
        try {
            return super.invokeMethod(name, args)
        } catch (MissingMethodException e) {
            var map = mappings.methods.get(theClass.name)
            if (map!=null) {
                // Check whether the method is in the mappables
                // If it is, map it and invoke that method
                List<String> mapped = map.get(name)
                if (mapped!=null) for (String possible : mapped) {
                    try {
                        return super.invokeMethod(possible, args)
                    } catch (MissingMethodException ignored) {}
                }
            }
            throw e
        }
    }

    @Override
    Object invokeMethod(Class sender, Object receiver, String methodName, Object[] arguments, boolean isCallToSuper, boolean fromInsideClass) {
        try {
            return super.invokeMethod(sender, receiver, methodName, arguments, isCallToSuper, fromInsideClass)
        } catch (MissingMethodException e) {
            var map = mappings.methods.get(theClass.name)
            if (map!=null) {
                // Check whether the method is in the mappables
                // If it is, map it and invoke that method
                List<String> mapped = map.get(methodName)
                if (mapped!=null) for (String possible : mapped) {
                    try {
                        return super.invokeMethod(sender, receiver, methodName, arguments, isCallToSuper, fromInsideClass)
                    } catch (MissingMethodException ignored) {}
                }
            }
            throw e
        }
    }

    @Override
    void setProperty(Class sender, Object receiver, String messageName, Object messageValue, boolean useSuper, boolean fromInsideClass) {
        try {
            super.setProperty(sender, receiver, messageName, messageValue, useSuper, fromInsideClass)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(messageName)
                if (mapped!=null) super.setProperty(sender, receiver, mapped, messageValue, useSuper, fromInsideClass)
                return
            }
            throw e
        }
    }

    @Override
    void setProperty(Object object, String property, Object newValue) {
        try {
            super.setProperty(object, property, newValue)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(property)
                if (mapped!=null) super.setProperty(object, mapped, newValue)
                return
            }
            throw e
        }
    }

    @Override
    void setProperty(String propertyName, Object newValue) {
        try {
            super.setProperty(propertyName, newValue)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(propertyName)
                if (mapped!=null) super.setProperty(mapped, newValue)
                return
            }
            throw e
        }
    }

    @Override
    Object getProperty(Class sender, Object receiver, String messageName, boolean useSuper, boolean fromInsideClass) {
        try {
            return super.getProperty(sender, receiver, messageName, useSuper, fromInsideClass)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(messageName)
                if (mapped!=null) return super.getProperty(sender,receiver,mapped,useSuper,fromInsideClass)
            }
            throw e
        }
    }

    @Override
    Object getProperty(String propertyName) {
        try {
            return super.getProperty(propertyName)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(propertyName)
                if (mapped!=null) return super.getProperty(mapped)
            }
            throw e
        }
    }

    @Override
    Object getProperty(Object object, String property) {
        try {
            return super.getProperty(object, property)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(property)
                if (mapped!=null) return super.getProperty(object, mapped)
            }
            throw e
        }
    }

    @Override
    Object getAttribute(Object object, String attribute) {
        try {
            return super.getAttribute(object, attribute)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(attribute)
                if (mapped!=null) return super.getAttribute(object, mapped)
            }
            throw e
        }
    }

    @Override
    Object getAttribute(Class sender, Object receiver, String messageName, boolean useSuper) {
        try {
            return super.getAttribute(sender, receiver, messageName, useSuper)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(messageName)
                if (mapped!=null) return super.getAttribute(sender, receiver, mapped, useSuper)
            }
            throw e
        }
    }

    @Override
    void setAttribute(Object object, String attribute, Object newValue) {
        try {
            super.setAttribute(object, attribute, newValue)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(attribute)
                if (mapped!=null) super.setAttribute(object, mapped, newValue)
                return
            }
            throw e
        }
    }

    @Override
    void setAttribute(Class sender, Object receiver, String messageName, Object messageValue, boolean useSuper, boolean fromInsideClass) {
        try {
            super.setAttribute(sender, receiver, messageName, messageValue, useSuper, fromInsideClass)
        } catch (MissingPropertyException e) {
            var map = mappings.fields.get(theClass.name)
            if (map!=null) {
                // Check whether the field is in the mappables
                // If it is, map it and invoke that method
                String mapped = map.get(messageName)
                if (mapped!=null) super.setAttribute(sender, receiver, mapped, messageValue, useSuper, fromInsideClass)
                return
            }
            throw e
        }
    }

    // Now stuff that isn't just direct get/set/invoke (metaprogramming, yay!)
    @Override
    MetaProperty getMetaProperty(String name) {
        MetaProperty old = super.getMetaProperty(name)
        if (old != null) return old

        //Yeah, I'll implement this later...

        return null
    }
}
