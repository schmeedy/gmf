package org.eclipse.gmf.tests.runtime.diagram.ui.logic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.examples.runtime.diagram.logic.internal.providers.LogicConstants;
import org.eclipse.gmf.examples.runtime.diagram.logic.model.Circuit;
import org.eclipse.gmf.examples.runtime.diagram.logic.model.LED;
import org.eclipse.gmf.examples.runtime.diagram.logic.model.SemanticPackage;
import org.eclipse.gmf.examples.runtime.diagram.logic.model.Terminal;
import org.eclipse.gmf.examples.runtime.diagram.logic.model.Wire;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalShapeCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tests.runtime.diagram.ui.AbstractTestBase;

/**
 * Tests the canonical editpolicies installed on the class attribute and 
 * operation list compartments.
 * @author mhanner
 */
public class LogicCanonicalTests extends AbstractTestBase {
	
	private static class CircuitCompartmentCanonicalEditPolicy extends CanonicalShapeCompartmentEditPolicy {

		protected List getSemanticChildrenList() {
			EObject modelRef = resolveSemanticElement();
			
			Circuit circuitElement = (Circuit) modelRef;
			if (circuitElement==null)
				return Collections.EMPTY_LIST;
			List allChildren = circuitElement.getChildren();
			List ledElements = new ArrayList();
			
			ListIterator li = allChildren.listIterator();
			while (li.hasNext()) {
				Object obj = li.next();
				if (obj instanceof LED)
					ledElements.add(obj);
			}
			
			return ledElements;
		}
		
		protected List getSemanticConnectorsList() {
			EObject modelRef = resolveSemanticElement();
			
			Circuit circuitElement = (Circuit) modelRef;
			if (circuitElement==null)
				return Collections.EMPTY_LIST;
			List allChildren = circuitElement.getChildren();
			ListIterator li = allChildren.listIterator();
			UniqueEList wires = new UniqueEList();
			while (li.hasNext()) {
				Object obj = li.next();
				if (obj instanceof Wire) {
					Wire wire = (Wire)obj;
					wires.add(wire);
				}
			}
			
			return wires;
		}

		protected boolean shouldDeleteView(View view) {
			return true;
		}
		
		protected EObject getSourceElement(EObject relationship) {
			Wire wire = (Wire)relationship;
			return wire.getSource();
		}

		protected EObject getTargetElement(EObject relationship) {
			Wire wire = (Wire)relationship;
			return wire.getTarget();
		}
		
	}
	
	/**
	 * Defines the statechart diagram test suite.
	 * 
	 * @return the test suite.
	 */
	public static Test suite() {
		TestSuite s = new TestSuite(LogicCanonicalTests.class);
		return s;
	}
	
	/** Create an instance. */
	public LogicCanonicalTests() {
		super("Canonical Test Suite");//$NON-NLS-1$
	}

	/** installs the composite state test fixture. */
	protected void setTestFixture() {
		testFixture = new CanonicalTestFixture();
	}

	/** Return <code>(CanonicalTestFixture)getTestFixture();</code> */
	protected CanonicalTestFixture getCanonicalTestFixture() {
		return (CanonicalTestFixture)getTestFixture();
	}
	
	private IGraphicalEditPart getCanonicalCompartment(int index) {
		CanonicalTestFixture fixture = getCanonicalTestFixture();
		
		List circuits = fixture.getShapes( SemanticPackage.eINSTANCE.getCircuit());
		assertTrue( "Failed to create circuit shapes.", !circuits.isEmpty() );//$NON-NLS-1$
		
		IGraphicalEditPart circuitEP = (IGraphicalEditPart)circuits.get(index);
		IGraphicalEditPart logicCompartment = circuitEP.getChildBySemanticHint(LogicConstants.LOGIC_SHAPE_COMPARTMENT);
		assertTrue( "unexpected children", logicCompartment.getChildren().isEmpty() );//$NON-NLS-1$
		
		logicCompartment.installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
			new CircuitCompartmentCanonicalEditPolicy());
		
		return logicCompartment;
	}
	
	/** 
	 * Tests the ability to disable the canonical editpolicy on  the 
	 * attribute list compartment.
	 */
	public void test_DisableCanonical() {
		try {
			println("test_DisableCanonical() starting ...");//$NON-NLS-1$
			
			IGraphicalEditPart logicCompartment = getCanonicalCompartment(0);
			getCanonicalTestFixture().enableCanonical( logicCompartment, false );
			final int SIZE = logicCompartment.getChildren().size();
			int count = 5;
			for ( int i = 0; i < count; i++ ) {
				getCanonicalTestFixture().createLED(ViewUtil.resolveSemanticElement(logicCompartment.getNotationView()));
				assertEquals( "Unexpected LED", SIZE, logicCompartment.getChildren().size() );//$NON-NLS-1$
			}
			
			getCanonicalTestFixture().enableCanonical( logicCompartment, true );
			assertEquals( "Unexpected LED", count, logicCompartment.getChildren().size() );//$NON-NLS-1$
		}
		finally {
			println("test_DisableCanonical() complete.");//$NON-NLS-1$
		}
	}
	
	/**
	 * Tests if the canonical editpolicy will refresh when the list compartment
	 * is collapsed.
	 */
	public void test_RefreshWhileCollapsed() {
		try {
			println("test_RefreshWhileCollapsed() starting ...");//$NON-NLS-1$
			IGraphicalEditPart logicCompartment = getCanonicalCompartment(0);
			
			getCanonicalTestFixture().setCollapsed( (IResizableCompartmentEditPart)logicCompartment, true );
			final int SIZE = logicCompartment.getChildren().size();
			int count = 5;
			for ( int i = 0; i < count; i++ ) {
				getCanonicalTestFixture().createLED(ViewUtil.resolveSemanticElement(logicCompartment.getNotationView()));
				assertEquals( "Unexpected LED", SIZE, logicCompartment.getChildren().size() );//$NON-NLS-1$
			}
			getCanonicalTestFixture().setCollapsed( (IResizableCompartmentEditPart)logicCompartment, false );
			assertEquals( "Unexpected LED", count, logicCompartment.getChildren().size() );//$NON-NLS-1$
			
		}
		finally {
			println("test_RefreshWhileCollapsed() complete.");//$NON-NLS-1$
		}
	}
	
	
	/**
	 * Tests if the canonical editpolicy will refresh when the list compartment
	 * is not visible.
	 */
	public void test_RefreshWhileVisible() {
		try {
			println("test_RefreshWhileVisible() starting ...");//$NON-NLS-1$
			IGraphicalEditPart logicCompartment = getCanonicalCompartment(0);
			
			getCanonicalTestFixture().setVisible( logicCompartment, false );
			final int SIZE = logicCompartment.getChildren().size();
			int count = 5;
			for ( int i = 0; i < count; i++ ) {
				getCanonicalTestFixture().createLED(ViewUtil.resolveSemanticElement(logicCompartment.getNotationView()));
				assertEquals( "Unexpected LED", SIZE, logicCompartment.getChildren().size() );//$NON-NLS-1$
			}
			getCanonicalTestFixture().setVisible( logicCompartment, true );
			assertEquals( "Unexpected LED", count, logicCompartment.getChildren().size() );//$NON-NLS-1$
		}
		finally {
			println("test_RefreshWhileVisible() complete.");//$NON-NLS-1$
		}
	}
	
	/**
	 * Tests the creation and deletion of an attribute view based on the
	 * creation and destruction of semantic elements.
	 */
	public void test_AddRemoveLED() {
		try {
			println("test_AddRemoveLED() starting ...");//$NON-NLS-1$
			IGraphicalEditPart logicCompartment = getCanonicalCompartment(0);
			
			List properties = new ArrayList();
			int size = logicCompartment.getChildren().size();
			int count = 5;
			for ( int i = 0; i < count; i++ ) {
				properties.add( getCanonicalTestFixture().createLED(ViewUtil.resolveSemanticElement(logicCompartment.getNotationView())));
				size++;
				assertEquals( "Unexpected LED count.", size, logicCompartment.getChildren().size() );//$NON-NLS-1$
			}
			
			size = logicCompartment.getChildren().size();
			EObject[] toDelete = new EObject[ properties.size() ];
			properties.toArray( toDelete );
			
			for ( int i = 0; i < toDelete.length; i++ ) {
				getCanonicalTestFixture().destroy( toDelete[i] );
				size--;
				assertEquals( "Unexpected LED count.", size, logicCompartment.getChildren().size() );//$NON-NLS-1$
			}
		}
		finally {
			println("test_AddRemoveAttribute() complete.");//$NON-NLS-1$
		}
	}
	
	public void test_AddDeleteWire() {
		try {
			println("test_AddDeleteWire() starting ...");//$NON-NLS-1$
			IGraphicalEditPart logicCompartment = getCanonicalCompartment(0);
			
			LED led1 = getCanonicalTestFixture().createLED(ViewUtil.resolveSemanticElement(logicCompartment.getNotationView()));
			LED led2 = getCanonicalTestFixture().createLED(ViewUtil.resolveSemanticElement(logicCompartment.getNotationView()));
			Terminal term1 = (Terminal)led1.getOutputTerminals().get(0);
			Terminal term2 = (Terminal)led2.getInputTerminals().get(0);
			
			IElementType typeWire = ElementTypeRegistry.getInstance().getType("logic.wire"); //$NON-NLS-1$
			IElementType typeCircuit = ElementTypeRegistry.getInstance().getType("logic.circuit"); //$NON-NLS-1$
			
			CreateRelationshipRequest crr = new CreateRelationshipRequest(term1, term2, typeWire);
			ICommand createWire = typeCircuit.getEditHelper().getEditCommand(crr);
			getCanonicalTestFixture().execute(createWire);
			flushEventQueue();
			
			List connectorEPs = getDiagramEditPart().getConnectors();
			
			assertEquals( "Unexpected Wire count.", 1, connectorEPs.size()); //$NON-NLS-1$
			ConnectionEditPart ep = (ConnectionEditPart)connectorEPs.get(0);
			assertTrue( "Unexpected source.", ((View)ep.getSource().getModel()).getElement().equals(term1));//$NON-NLS-1$
			assertTrue( "Unexpected target.", ((View)ep.getTarget().getModel()).getElement().equals(term2));//$NON-NLS-1$
			assertTrue(((View)ep.getModel()).getElement() instanceof Wire);
			
			// now destroy it
			getCanonicalTestFixture().destroy( ((View)ep.getModel()).getElement() );
			flushEventQueue();
			
			connectorEPs = getDiagramEditPart().getConnectors();
			assertEquals( "Unexpected Wire count.", 0, connectorEPs.size()); //$NON-NLS-1$
		}
		finally {
			println("test_AddDeleteWire() complete.");//$NON-NLS-1$
		}
	}
		

	/**
	 * Tests the creation and deletion of an attribute view based on
	 * moving an semantic attribute between classes.
	 */
	public void test_ReparentLED() {
		try {
			println("test_ReparentLED() starting ...");//$NON-NLS-1$
			CanonicalTestFixture fixture = (CanonicalTestFixture)getTestFixture();
			
			IGraphicalEditPart logicCompartment = getCanonicalCompartment(0);
			IGraphicalEditPart logicCompartment2 = getCanonicalCompartment(1);
			Circuit circuit1 = (Circuit)ViewUtil.resolveSemanticElement(logicCompartment.getNotationView());
			Circuit circuit2 = (Circuit)ViewUtil.resolveSemanticElement(logicCompartment2.getNotationView());
			
			LED movingLED = fixture.createLED(circuit1);
			flushEventQueue();
			assertEquals( "Unexpected LED count.", 1, logicCompartment.getChildren().size() );//$NON-NLS-1$
			
			MoveRequest mr = new MoveRequest(circuit2, movingLED);
			IElementType typeCircuit = ElementTypeRegistry.getInstance().getType("logic.circuit"); //$NON-NLS-1$
			ICommand reparentCmd = typeCircuit.getEditHelper().getEditCommand(mr);
			fixture.execute(reparentCmd);
			flushEventQueue();
			
			assertTrue( "unexpected LED", logicCompartment.getChildren().isEmpty() );//$NON-NLS-1$
			assertEquals( "Unexpected LED count.", 1, logicCompartment2.getChildren().size() );//$NON-NLS-1$
			
			mr = new MoveRequest(circuit1, movingLED);
			reparentCmd = typeCircuit.getEditHelper().getEditCommand(mr);
			fixture.execute(reparentCmd);
			flushEventQueue();
			
			assertTrue( "unexpected LED", logicCompartment2.getChildren().isEmpty() );//$NON-NLS-1$
			assertEquals( "Unexpected LED count.", 1, logicCompartment.getChildren().size() );//$NON-NLS-1$
		}
		finally {
			println("test_ReparentLED() complete.");//$NON-NLS-1$
		}
	}

}
