package com.hizliyol.core.controller;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class GraphController {

	private LineChartModel lineModel;
	private LineChartModel animatedModel1;

	@PostConstruct
	public void init(){

		createFirstLinearChart();
		createAnimatedModels();
	}
	

	private void createFirstLinearChart() {
		lineModel = initCategoryModel();
		lineModel.setTitle("Category Chart");
		lineModel.setLegendPosition("e");
		lineModel.setShowPointLabels(true);
		lineModel.getAxes().put(AxisType.X, new CategoryAxis("Years"));
		Axis yAxis = lineModel.getAxis(AxisType.Y);
		yAxis.setLabel("Births");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private LineChartModel initCategoryModel() {
		LineChartModel model = new LineChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Boys");
		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Girls");
		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 90);
		girls.set("2008", 120);

		model.addSeries(boys);
		model.addSeries(girls);

		return model;
	}

	private void createAnimatedModels() {
		animatedModel1 = initLinearModel();
		animatedModel1.setTitle("Line Chart");
		animatedModel1.setAnimate(true);
		animatedModel1.setLegendPosition("se");
		Axis yAxis = animatedModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");

		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);

		model.addSeries(series1);
		model.addSeries(series2);

		return model;
	}


	public LineChartModel getLineModel() {
		return lineModel;
	}


	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}


	public LineChartModel getAnimatedModel1() {
		return animatedModel1;
	}


	public void setAnimatedModel1(LineChartModel animatedModel1) {
		this.animatedModel1 = animatedModel1;
	}

	
}
