/*
 * Copyright (C) 2016 The Android Open Source Project
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

package com.android.tools.adtui.model;

import com.android.tools.adtui.common.formatter.BaseAxisFormatter;
import com.android.tools.adtui.Range;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a view into a continuous series, where the data in view is only
 * within given x and y ranged.
 */
public class RangedContinuousSeries implements ReportingSeries {
  @NotNull
  private final Range mXRange;

  @NotNull
  private final Range mYRange;

  @NotNull
  private final ContinuousSeries mSeries;

  @NotNull
  private final String mLabel;

  private BaseAxisFormatter mXAxisFormatter;

  private BaseAxisFormatter mYAxisFormatter;

  public RangedContinuousSeries(@NotNull String label, @NotNull Range xRange, @NotNull Range yRange, @NotNull ContinuousSeries series) {
    mLabel = label;
    mXRange = xRange;
    mYRange = yRange;
    mSeries = series;
  }

  public RangedContinuousSeries(@NotNull String label, @NotNull Range xRange, @NotNull Range yRange) {
    this(label, xRange, yRange, new ContinuousSeries());
  }

  public RangedContinuousSeries(@NotNull String label, @NotNull Range xRange, @NotNull Range yRange,
                                @NotNull BaseAxisFormatter xAxisFormatter, @NotNull BaseAxisFormatter yAxisFormatter) {
    this(label, xRange, yRange);
    mXAxisFormatter = xAxisFormatter;
    mYAxisFormatter = yAxisFormatter;
  }

  @NotNull
  public ContinuousSeries getSeries() {
    return mSeries;
  }

  @NotNull
  public Range getYRange() {
    return mYRange;
  }

  @NotNull
  public Range getXRange() {
    return mXRange;
  }

  @Override
  public double getRangeLength() {
    return mYRange.getLength();
  }

  @Override
  public double getLatestValue() {
    double value = 0.0;
    if (mSeries.size() != 0) {
      value = mSeries.getY(mSeries.size() - 1);
    }
    return value;
  }

  private ReportingData getReportingDataForIndex(int index) {
    assert index >= 0 && index < mSeries.size();
    long nearestX = mSeries.getX(index);
    long nearestY = mSeries.getY(index);
    long maxX = mSeries.getMaxX();
    long maxY = mSeries.getMaxY();

    String formattedY = mYAxisFormatter == null ? Long.toString(nearestY) : mYAxisFormatter.getFormattedString(maxY, nearestY);
    String formattedX = mXAxisFormatter == null ? Long.toString(nearestX) : mXAxisFormatter.getFormattedString(maxX, nearestX);
    return new ReportingData(nearestX, mLabel, formattedX, formattedY);
  }

  @Override
  public ReportingData getLatestReportingData() {
    return mSeries.size() == 0 ? null : getReportingDataForIndex(mSeries.size()-1);
  }

  @Override
  public Collection<ReportingData> getFullReportingData(long x) {
    ArrayList<ReportingData> dataList = new ArrayList<>();

    int nearestIndex = mSeries.getNearestXIndex(x);
    if (nearestIndex >= 0) {
      dataList.add(getReportingDataForIndex(nearestIndex));
    }
    return dataList;
  }
}
