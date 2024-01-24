package sorcer.sml.flatfinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.flatfinder.*;
import sorcer.service.Block;
import sorcer.service.Signature;
import sorcer.service.Task;

import static org.junit.Assert.assertEquals;
import static sorcer.co.operator.inVal;
import static sorcer.eo.operator.*;
import static sorcer.eo.operator.context;
import static sorcer.mo.operator.value;
import static sorcer.so.operator.exert;

@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/sml")
public class FlatFinderMograms {
    @Test
    public void flatFinderFindBestFlatAndGetDetailsStructuredBlockV2() throws Exception {
        // Arrange
        Task findBestFlatTask = task("findBestFlatTask", sig("findBest", FlatFinderImpl.class),
                context("findBest", inVal(FlatFinderImpl.MAX_DISTANCE_ARG_ID),
                        result(FlatFinderImpl.FLAT_ARG_ID, Signature.Direction.IN)));

        Task findBestTenantTask = task("findBestTenantTask", sig("findBest", TenantFinderImpl.class),
                context("findBest", inVal(""),
                        result(TenantFinderImpl.TENANT_ARG_ID, Signature.Direction.IN)));

        Task addTenantTask = task("addTenantTask", sig("addTenant", FlatFinderImpl.class),
                context("addTenant", inVal(FlatFinderImpl.FLAT_ARG_ID), inVal(TenantFinderImpl.TENANT_ARG_ID),
                        result(FlatFinderImpl.FLAT_TENANT_RESULT, Signature.Direction.OUT)));

        Task findByFlatNameTask = task("findByFlatNameTask", sig("findByFlatName", OwnerFinderImpl.class),
                context("findByFlatName", inVal(OwnerFinderImpl.OWNER_ARG_FLATNAME),
                        result(OwnerFinderImpl.OWNER_RESULT, Signature.Direction.OUT)));

        Block block1 = block("block1", block(findBestFlatTask, findBestTenantTask), addTenantTask,
                context(inVal(FlatFinderImpl.MAX_DISTANCE_ARG_ID, 49), inVal("")));

        Block block2 = block("block2", findByFlatNameTask,
                context(inVal(OwnerFinderImpl.OWNER_ARG_FLATNAME, "Potulicka")));

        // Act
        Block result1 = exert(block1);
        Block result2 = exert(block2);

        // Assert
        assertEquals(value(context(result1), FlatFinderImpl.FLAT_ARG_ID), value(context(result1), FlatFinderImpl.FLAT_TENANT_RESULT));
        assertEquals("Michael", ((Owner)(value(context(result2), OwnerFinderImpl.OWNER_RESULT))).getFirstName());
    }
}
