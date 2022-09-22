package org.example.jackson.bench;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class IntArrayPoolFactory extends BasePooledObjectFactory<int[]> {
    @Override
    public int[] create() throws Exception {
        return new int[32];
    }

    @Override
    public PooledObject<int[]> wrap(int[] arr) {
        return new DefaultPooledObject<int[]>(arr);
    }

    /**
     * When an array is returned to the pool, zero all the ints in the array.
     */
    @Override
    public void passivateObject(PooledObject<int[]> pooledObject) {
        final int[] arr = pooledObject.getObject();
        for (int i = 0 ; i < arr.length; i++) {
            arr[i] = 0;
        }
    }
}
