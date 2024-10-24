// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.android.ads.nativetemplates;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

import org.w3c.dom.Text;

/**
 * Base class for a template view. *
 */
public class TemplateView extends FrameLayout {

    private int templateType;
    private NativeTemplateStyle styles;
    private NativeAd nativeAd;
    private NativeAdView nativeAdView;

    private TextView primaryView;
    private TextView secondaryView;
    private RatingBar ratingBar;
    private TextView tertiaryView;
    private ImageView iconView;
    private MediaView mediaView;
    private Button callToActionView;
    private TextView rating;
    private TextView price;


    public TemplateView(Context context) {
        super(context);
    }

    public TemplateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TemplateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public TemplateView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private boolean adHasOnlyStore(NativeAd nativeAd) {
        String store = nativeAd.getStore();
        String advertiser = nativeAd.getAdvertiser();
        return !TextUtils.isEmpty(store) && TextUtils.isEmpty(advertiser);
    }

    public void setNativeAd(NativeAd nativeAd) {
        this.nativeAd = nativeAd;

        String store = nativeAd.getStore();
        String advertiser = nativeAd.getAdvertiser();
        String headline = nativeAd.getHeadline();
        String body = nativeAd.getBody();
        String cta = nativeAd.getCallToAction();
        NativeAd.Image icon = nativeAd.getIcon();
        String mprice = nativeAd.getPrice();
        String mRating = nativeAd.getStarRating().toString();

        String secondaryText;

        nativeAdView.setCallToActionView(callToActionView);
        nativeAdView.setHeadlineView(primaryView);
        nativeAdView.setMediaView(mediaView);
        nativeAdView.setPriceView(price);
        nativeAdView.setStarRatingView(rating);
        try {
            secondaryView.setVisibility(VISIBLE);
            if (adHasOnlyStore(nativeAd)) {
                nativeAdView.setStoreView(secondaryView);
                secondaryText = store;
                secondaryView.setText(secondaryText);
                secondaryView.setVisibility(VISIBLE);
            } else if (!TextUtils.isEmpty(advertiser)) {
                nativeAdView.setAdvertiserView(secondaryView);
                secondaryText = advertiser;
                secondaryView.setText(secondaryText);
                secondaryView.setVisibility(VISIBLE);
            } else {
                secondaryText = "";
                secondaryView.setText(secondaryText);
                secondaryView.setVisibility(VISIBLE);
            }
            if(!TextUtils.isEmpty(mRating)){
                rating.setText(mRating);
            }
            if(!TextUtils.isEmpty(mprice)){
                price.setText(mprice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryView.setText(headline);
        callToActionView.setText(cta);

        if (icon != null) {
            iconView.setVisibility(VISIBLE);
            iconView.setImageDrawable(icon.getDrawable());
        } else {
            iconView.setVisibility(INVISIBLE);
        }

        if (tertiaryView != null) {
            tertiaryView.setText(body);
            nativeAdView.setBodyView(tertiaryView);
        }

        nativeAdView.setNativeAd(nativeAd);
    }

    private void initView(Context context, AttributeSet attributeSet) {

        TypedArray attributes =
                context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.TemplateView, 0, 0);

        try {
            templateType =
                    attributes.getResourceId(
                            R.styleable.TemplateView_gnt_template_type, R.layout.gnt_medium_template_view);
        } finally {
            attributes.recycle();
        }
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(templateType, this);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        nativeAdView = (NativeAdView) findViewById(R.id.native_ad_view);
        primaryView = (TextView) findViewById(R.id.primary);
        try {
            secondaryView = (TextView) findViewById(R.id.secondary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tertiaryView = (TextView) findViewById(R.id.body);

        callToActionView = (Button) findViewById(R.id.cta);


        iconView = (ImageView) findViewById(R.id.icon);
        mediaView = (MediaView) findViewById(R.id.media_view);

        rating = (TextView) findViewById(R.id.rating);
        price = (TextView) findViewById(R.id.free);
    }

}

