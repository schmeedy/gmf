Instructions when adding new public API/extension-points:

1) Configure workstation for building Javadoc
	Download Sun JDK 1.4.2_05 from Hursley (http://w3.hursley.ibm.com/java/jim/j2se/sunreference/sunreferenceportsforwindowsjdk/14205/)
		Extract in c:\jdk1.4.2_05
	Download the documentation from the same link and extract in c:\jdk1.4.2_05\docs
	Add "c:\jdk1.4.2_05\bin" to your system path environment variable.
		Relaunch Eclipse if it is already started so it picks up the environment variable change

2) Update buildDoc.xml and buildSource.xml (found in \r_aurora_gui\com.ibm.xtools.platform.doc.isv and \r_aurora_gui\com.ibm.xtools.platform.source respectively) 

	a. Add an entry for the extension-points you want to publish in the following element:
		<patternset id="publish.extension-point.set">
		e.g. 
		<!-- Viz -->
		
		<include name="com.ibm.xtools.viz.core/doc/com_ibm_xtools_viz_core_CodeProviders.html"/>
		<include name="com.ibm.xtools.viz.core/doc/com_ibm_xtools_viz_core_UMLVisualizationProviders.html"/>
		<include name="com.ibm.xtools.viz.core/doc/com_ibm_xtools_viz_core_VizGeneratedFactory.html"/>
		<include name="com.ibm.xtools.viz.core/doc/com_ibm_xtools_viz_core_VizRefHandlers.html"/>


	b. Add <packageset> entries for the plug-ins you want to generate Javadoc for
	   in the "doJavadoc" target.  Be sure to add your plug-ins into the appropriate
	   <javadoc-subsystem> macro invocation, according to your domain (or finer
	   granularity subsystem).
	   
	   If necessary, you can add a new <javadoc-subsystem> invocation for another
	   domain/subsystem, modeled after an existing example.  If you do this, you will
	   need to add another topic link in the toc.xml file and another <link> element
	   in the "javadoc-subsystem" macro definition.
	   e.g. (in buildDoc.xml) NOTE: Order matters, the entries are built sequentially as they appear in this target
		<javadoc-subsystem subsystem="uml">
		    <package-sets>
		        <packageset dir="../com.ibm.xtools.uml.msl/src"/>
		        <packageset dir="../com.ibm.xtools.uml.validation/src"/>
		        <packageset dir="../com.ibm.xtools.uml.validation.ocl/src"/>
		    </package-sets>
		    <package-groups>
		        <group title="UML Metamodel Packages" packages="com.ibm.xtools.uml.msl*"/>
		        <group title="UML Core Framework Packages" packages="com.ibm.xtools.uml.core*,com.ibm.xtools.uml.ui*"/>
		        <group title="UML Validation Packages" packages="com.ibm.xtools.uml.validation*"/>
		    </package-groups>
		</javadoc-subsystem>
		(in toc.xml)
		<topic label="UML Metamodel Layer"
		href="reference/api/uml/overview-summary.html" />
	   
	   Also, depending on the plug-in dependencies, you may need to add third-party
	   JARs to the classpath in the "computeClasspath" target and a corresponding
	   <link> element in the "javadoc-subsystem" macro definition to link to its
	   javadocs.
	   e.g.
		<include name="**/plugins/org.eclipse.core*/**/*.jar"/>
		<include name="**/plugins/org.eclipse.emf*/**/*.jar"/>
		<include name="**/plugins/org.eclipse.emf.ocl.engine*/**/*.jar"/>

	c. Update the project references to add a dependency from this plug-in to
	   the plug-in that you are adding to the documentation.  This is needed so
	   that the latter plug-in gets built before this plug-in.  This way, your
	   plug-in's bin directory will be available for the javadoc tool's classpath
	   and its schema documentation will be generated before this plug-in includes
	   it in the doc.zip.
				
	d. In the "gather.source" target of the buildSource.xml file in the
	   com.ibm.xtools.platform.source project, add an entry for your plug-in:
	
		e.g.    
			<!-- org.eclipse.gmf.runtime.common.core -->
		    <antcall target="build.source.zip">
	    		  <param name="plugin.id" value="org.eclipse.gmf.runtime.common.core"/>		<- Name of folder containing plug-in
			      <param name="plugin.jar" value="xtoolscore"/>						<--name of the plug-in jar file (without extension)
			      <param name="plugin.version" value="6.0.0"/>						<-- Version of plug-in
			</antcall>
	
	e. Add reference to your extension points in \html\reference\extension-points\index.html

	e.g.
	<h3><a name=DiagramLayer></a>Diagram Layer</h3>
	<ul>
		<li><a href="com_ibm_xtools_diagram_semanticProviders.html">org.eclipse.gmf.runtime.diagram.core.semanticProviders</a></li>
		<li><a href="com_ibm_xtools_diagram_viewProviders.html">org.eclipse.gmf.runtime.diagram.core.viewProviders</a></li> 
		<li><a href="com_ibm_xtools_diagram_ui_editpartProviders.html">org.eclipse.gmf.runtime.diagram.ui.editpartProviders</a></li>
		<li><a href="com_ibm_xtools_diagram_ui_editpolicyProviders.html">org.eclipse.gmf.runtime.diagram.ui.editpolicyProviders</a></li>
		<li><a href="com_ibm_xtools_diagram_ui_paletteProviders.html">org.eclipse.gmf.runtime.diagram.ui.paletteProviders</a></li>
		<li><a href="com_ibm_xtools_diagram_ui_semanticProviders.html">org.eclipse.gmf.runtime.diagram.ui.semanticProviders</a></li>
		<li><a href="com_ibm_xtools_diagram_ui_viewProviders.html">org.eclipse.gmf.runtime.diagram.ui.viewProviders</a></li>
		<li><a href="com_ibm_xtools_diagram_ui_browse_topicProviders.html">com.ibm.xtools.diagram.ui.browse.topicProviders</a></li>
		<li><a href="com_ibm_xtools_presentation_decoratorProviders.html">com.ibm.xtools.presentation.decoratorProviders</a></li>
		<li><a href="com_ibm_xtools_presentation_editpartProviders.html">com.ibm.xtools.presentation.editpartProviders</a></li>
		<li><a href="com_ibm_xtools_presentation_editpolicyProviders.html">com.ibm.xtools.presentation.editpolicyProviders</a></li>
		<li><a href="com_ibm_xtools_presentation_layoutProviders.html">com.ibm.xtools.presentation.layoutProviders</a></li>
		<li><a href="com_ibm_xtools_presentation_paletteProviders.html">com.ibm.xtools.presentation.paletteProviders</a></li>
		<li><a href="com_ibm_xtools_presentation_semanticProviders.html">com.ibm.xtools.presentation.semanticProviders</a></li>
		<li><a href="com_ibm_xtools_presentation_topicProviders.html">com.ibm.xtools.presentation.topicProviders</a></li>
		<li><a href="com_ibm_xtools_presentation_viewProviders.html">com.ibm.xtools.presentation.viewProviders</a></li>
	</ul>
	
	f. Add any public IDs in html\reference\misc\publicIds.html
	
	g. To build the project, clean and manually rebuild.
	   You might first delete the C:\temp\javadoc.link.location\workspace.jar
	   file which contains all of the compiled code from your workspace, if
	   it is out of date and needs to be rebuilt.
	
		**** There should be NO javadoc warning - please keep this clean ****

3) Update TOC files

	If needed, update toc.xml
	Add reference to your extension points in the appropriate TOC file; e.g. \toc-common-ext-pt.xml
	
	NOTE: javadoc package TOC link are automatically generated for you by the Aurora Doclet

4) Test Javadoc
	Run the tool and look into "Help > Help Contents", into the book named "Rational Modeling Platform Developer's Guide"
	Your topic should be visible under "API Reference" and "Extension Points Reference"
	Make sure a TOC entry exists for each published package
	Make sure a TOC entry exists for your new extension-point
	Links from your API to Eclipse, EMF, UML2, GEF, Draw2D, and Sun JDK should available and working
	

5) Test JDT/PDE integration
	a. Deploy all plug-ins in an installed Eclipse
		NOTE: Self-hosting will not work here since Workspace plug-ins are not seen by runtime PDE as normal external plug-in
	b. Create a PDE plug-in Java project that references your plug-in
	c. Open the java editor and verify that auto-complete works with your classes
	d. Open the plug-in manifest editor, add an extension to your newly added extension-point
	   right click; both "Show Description" and "Declaration" should work as expected.
	
	


