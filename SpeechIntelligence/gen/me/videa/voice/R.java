/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package me.videa.voice;

public final class R {
    public static final class anim {
        public static final int loading=0x7f040000;
        public static final int slide_in_from_bottom=0x7f040001;
        public static final int slide_in_from_top=0x7f040002;
        public static final int slide_out_to_bottom=0x7f040003;
        public static final int slide_out_to_top=0x7f040004;
    }
    public static final class array {
        /**  合成 
         */
        public static final int engine_entries=0x7f090000;
        public static final int engine_values=0x7f090001;
    }
    public static final class attr {
        /**  BELOW HERE ARE DEPRECEATED. DO NOT USE. 
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
         */
        public static final int ptrAdapterViewBackground=0x7f010010;
        /**  Style of Animation should be used displayed when pulling. 
         <p>Must be one or more (separated by '|') of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>rotate</code></td><td>0x0</td><td></td></tr>
<tr><td><code>flip</code></td><td>0x1</td><td></td></tr>
</table>
         */
        public static final int ptrAnimationStyle=0x7f01000c;
        /**  Drawable to use as Loading Indicator. Changes both Header and Footer. 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int ptrDrawable=0x7f010006;
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int ptrDrawableBottom=0x7f010012;
        /**  Drawable to use as Loading Indicator in the Footer View. Overrides value set in ptrDrawable. 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int ptrDrawableEnd=0x7f010008;
        /**  Drawable to use as Loading Indicator in the Header View. Overrides value set in ptrDrawable. 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int ptrDrawableStart=0x7f010007;
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int ptrDrawableTop=0x7f010011;
        /**  A drawable to use as the background of the Header and Footer Loading Views 
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
         */
        public static final int ptrHeaderBackground=0x7f010001;
        /**  Text Color of the Header and Footer Loading Views Sub Header 
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
         */
        public static final int ptrHeaderSubTextColor=0x7f010003;
        /**  Base text color, typeface, size, and style for Header and Footer Loading Views 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int ptrHeaderTextAppearance=0x7f01000a;
        /**  Text Color of the Header and Footer Loading Views 
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
         */
        public static final int ptrHeaderTextColor=0x7f010002;
        /** 
        	Whether PullToRefreshListView has it's extras enabled. This allows the user to be 
        	able to scroll while refreshing, and behaves better. It acheives this by adding
        	Header and/or Footer Views to the ListView.
        
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
         */
        public static final int ptrListViewExtrasEnabled=0x7f01000e;
        /**  Mode of Pull-to-Refresh that should be used 
         <p>Must be one or more (separated by '|') of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>disabled</code></td><td>0x0</td><td></td></tr>
<tr><td><code>pullFromStart</code></td><td>0x1</td><td></td></tr>
<tr><td><code>pullFromEnd</code></td><td>0x2</td><td></td></tr>
<tr><td><code>both</code></td><td>0x3</td><td></td></tr>
<tr><td><code>manualOnly</code></td><td>0x4</td><td></td></tr>
<tr><td><code>pullDownFromTop</code></td><td>0x1</td><td> These last two are depreacted </td></tr>
<tr><td><code>pullUpFromBottom</code></td><td>0x2</td><td></td></tr>
</table>
         */
        public static final int ptrMode=0x7f010004;
        /**  Whether Android's built-in Over Scroll should be utilised for Pull-to-Refresh. 
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
         */
        public static final int ptrOverScroll=0x7f010009;
        /**  A drawable to use as the background of the Refreshable View 
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
         */
        public static final int ptrRefreshableViewBackground=0x7f010000;
        /** 
        	Whether the Drawable should be continually rotated as you pull. This only
        	takes effect when using the 'Rotate' Animation Style.
        
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
         */
        public static final int ptrRotateDrawableWhilePulling=0x7f01000f;
        /**  Whether the user can scroll while the View is Refreshing 
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
         */
        public static final int ptrScrollingWhileRefreshingEnabled=0x7f01000d;
        /**  Whether the Indicator overlay(s) should be used 
         <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
         */
        public static final int ptrShowIndicator=0x7f010005;
        /**  Base text color, typeface, size, and style for Header and Footer Loading Views Sub Header 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int ptrSubHeaderTextAppearance=0x7f01000b;
    }
    public static final class color {
        public static final int background=0x7f080001;
        public static final int black=0x7f080000;
        public static final int content_color=0x7f080006;
        /**  控件按下时显示的颜色 
         */
        public static final int list_backgroud_color=0x7f080004;
        public static final int title_color=0x7f080005;
        public static final int txtColor=0x7f080002;
        public static final int white=0x7f080003;
    }
    public static final class dimen {
        public static final int btn_size=0x7f060007;
        public static final int header_footer_left_right_padding=0x7f060003;
        public static final int header_footer_top_bottom_padding=0x7f060004;
        public static final int indicator_corner_radius=0x7f060001;
        public static final int indicator_internal_padding=0x7f060002;
        public static final int indicator_right_padding=0x7f060000;
        /**  分割线高度 
         */
        public static final int line_height=0x7f060009;
        public static final int margin_=0x7f060005;
        public static final int pading_=0x7f060006;
        public static final int txt_size=0x7f060008;
    }
    public static final class drawable {
        public static final int btn_background=0x7f020000;
        public static final int common_loading3_0=0x7f020001;
        public static final int common_loading3_1=0x7f020002;
        public static final int common_loading3_10=0x7f020003;
        public static final int common_loading3_11=0x7f020004;
        public static final int common_loading3_2=0x7f020005;
        public static final int common_loading3_3=0x7f020006;
        public static final int common_loading3_4=0x7f020007;
        public static final int common_loading3_5=0x7f020008;
        public static final int common_loading3_6=0x7f020009;
        public static final int common_loading3_7=0x7f02000a;
        public static final int common_loading3_8=0x7f02000b;
        public static final int common_loading3_9=0x7f02000c;
        public static final int default_deliver=0x7f02000d;
        public static final int default_ptr_flip=0x7f02000e;
        public static final int default_ptr_rotate=0x7f02000f;
        public static final int ic_code_scan_shortcut=0x7f020010;
        public static final int ic_lockscreen_message_activated=0x7f020011;
        public static final int ic_lockscreen_message_normal=0x7f020012;
        public static final int ic_lockscreen_phone_activated=0x7f020013;
        public static final int ic_lockscreen_phone_normal=0x7f020014;
        public static final int ic_lockscreen_unlock_activated=0x7f020015;
        public static final int ic_lockscreen_unlock_normal=0x7f020016;
        public static final int ic_tab_theme_normal=0x7f020017;
        public static final int ic_tab_theme_selected=0x7f020018;
        public static final int icon=0x7f020019;
        public static final int indicator_arrow=0x7f02001a;
        public static final int indicator_bg_bottom=0x7f02001b;
        public static final int indicator_bg_top=0x7f02001c;
        public static final int jarvis_bg=0x7f02001d;
        /**  画面背景色 
         */
        public static final int layout_backgroud=0x7f020026;
        /**  分割线背景色 
         */
        public static final int line_background=0x7f020027;
        public static final int list_bg_color=0x7f02001e;
        public static final int lock_bg=0x7f02001f;
        public static final int lockscre_pressed=0x7f020020;
        public static final int lockscreen_normal=0x7f020021;
        public static final int main_setting_btn_np=0x7f020022;
        public static final int setting=0x7f020023;
        public static final int setting_p=0x7f020024;
        public static final int template_checkbox_normal=0x7f020025;
    }
    public static final class id {
        public static final int both=0x7f050003;
        public static final int current_content=0x7f05000e;
        public static final int current_path=0x7f05000c;
        public static final int current_path_name=0x7f05000d;
        public static final int disabled=0x7f050000;
        public static final int file_name=0x7f05000f;
        public static final int file_size=0x7f050010;
        public static final int fl_inner=0x7f050011;
        public static final int flip=0x7f050008;
        public static final int gridview=0x7f050009;
        public static final int mainView=0x7f050017;
        public static final int main_layout=0x7f050016;
        public static final int manualOnly=0x7f050004;
        public static final int pullDownFromTop=0x7f050005;
        public static final int pullFromEnd=0x7f050002;
        public static final int pullFromStart=0x7f050001;
        public static final int pullUpFromBottom=0x7f050006;
        public static final int pull_to_refresh_image=0x7f050012;
        public static final int pull_to_refresh_progress=0x7f050013;
        public static final int pull_to_refresh_sub_text=0x7f050015;
        public static final int pull_to_refresh_text=0x7f050014;
        public static final int rotate=0x7f050007;
        public static final int scrollview=0x7f05000b;
        public static final int webview=0x7f05000a;
    }
    public static final class layout {
        public static final int activity_remote_file_show=0x7f030000;
        public static final int activity_remote_file_show_content=0x7f030001;
        public static final int activity_screen_lock=0x7f030002;
        public static final int pull_to_refresh_header_horizontal=0x7f030003;
        public static final int pull_to_refresh_header_vertical=0x7f030004;
        public static final int voice_main_activity=0x7f030005;
    }
    public static final class string {
        /**  请替换成在语音云官网申请的appid 
         */
        public static final int app_id=0x7f070010;
        public static final int app_name=0x7f07000f;
        public static final int cancel=0x7f07000d;
        public static final int cloud_lib=0x7f070007;
        public static final int current_content=0x7f07001c;
        /**  ********************************************** 
         */
        public static final int current_path=0x7f07001a;
        public static final int current_path_name=0x7f07001b;
        public static final int file_size=0x7f07001d;
        public static final int img_content=0x7f07001e;
        public static final int listen_type=0x7f07000a;
        public static final int load_err=0x7f07001f;
        public static final int local_lib=0x7f070008;
        public static final int main_title=0x7f070006;
        public static final int mix=0x7f070009;
        public static final int pref_key_iat_show=0x7f070017;
        public static final int pref_title_iat_dwa=0x7f070019;
        public static final int pref_title_iat_show=0x7f070018;
        /**  Just use standard Pull Down String when pulling up. These can be set for languages which require it 
 Just use standard Pull Down String when pulling up. These can be set for languages which require it 
         */
        public static final int pull_to_refresh_from_bottom_pull_label=0x7f070003;
        public static final int pull_to_refresh_from_bottom_refreshing_label=0x7f070005;
        public static final int pull_to_refresh_from_bottom_release_label=0x7f070004;
        public static final int pull_to_refresh_pull_label=0x7f070000;
        public static final int pull_to_refresh_refreshing_label=0x7f070002;
        public static final int pull_to_refresh_release_label=0x7f070001;
        public static final int start=0x7f07000b;
        public static final int start_service=0x7f07000e;
        public static final int stop=0x7f07000c;
        /**  听写 
         */
        public static final int text_begin=0x7f070011;
        public static final int text_download_success=0x7f070016;
        public static final int text_upload_contacts=0x7f070012;
        public static final int text_upload_success=0x7f070014;
        public static final int text_upload_userwords=0x7f070013;
        public static final int text_userword_empty=0x7f070015;
    }
    public static final class style {
        public static final int dialog=0x7f0a0000;
    }
    public static final class styleable {
        /** Attributes that can be used with a PullToRefresh.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #PullToRefresh_ptrAdapterViewBackground me.videa.voice:ptrAdapterViewBackground}</code></td><td> BELOW HERE ARE DEPRECEATED.</td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrAnimationStyle me.videa.voice:ptrAnimationStyle}</code></td><td> Style of Animation should be used displayed when pulling.</td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrDrawable me.videa.voice:ptrDrawable}</code></td><td> Drawable to use as Loading Indicator.</td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrDrawableBottom me.videa.voice:ptrDrawableBottom}</code></td><td></td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrDrawableEnd me.videa.voice:ptrDrawableEnd}</code></td><td> Drawable to use as Loading Indicator in the Footer View.</td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrDrawableStart me.videa.voice:ptrDrawableStart}</code></td><td> Drawable to use as Loading Indicator in the Header View.</td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrDrawableTop me.videa.voice:ptrDrawableTop}</code></td><td></td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrHeaderBackground me.videa.voice:ptrHeaderBackground}</code></td><td> A drawable to use as the background of the Header and Footer Loading Views </td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrHeaderSubTextColor me.videa.voice:ptrHeaderSubTextColor}</code></td><td> Text Color of the Header and Footer Loading Views Sub Header </td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrHeaderTextAppearance me.videa.voice:ptrHeaderTextAppearance}</code></td><td> Base text color, typeface, size, and style for Header and Footer Loading Views </td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrHeaderTextColor me.videa.voice:ptrHeaderTextColor}</code></td><td> Text Color of the Header and Footer Loading Views </td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrListViewExtrasEnabled me.videa.voice:ptrListViewExtrasEnabled}</code></td><td>
        	Whether PullToRefreshListView has it's extras enabled.</td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrMode me.videa.voice:ptrMode}</code></td><td> Mode of Pull-to-Refresh that should be used </td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrOverScroll me.videa.voice:ptrOverScroll}</code></td><td> Whether Android's built-in Over Scroll should be utilised for Pull-to-Refresh.</td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrRefreshableViewBackground me.videa.voice:ptrRefreshableViewBackground}</code></td><td> A drawable to use as the background of the Refreshable View </td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrRotateDrawableWhilePulling me.videa.voice:ptrRotateDrawableWhilePulling}</code></td><td>
        	Whether the Drawable should be continually rotated as you pull.</td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrScrollingWhileRefreshingEnabled me.videa.voice:ptrScrollingWhileRefreshingEnabled}</code></td><td> Whether the user can scroll while the View is Refreshing </td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrShowIndicator me.videa.voice:ptrShowIndicator}</code></td><td> Whether the Indicator overlay(s) should be used </td></tr>
           <tr><td><code>{@link #PullToRefresh_ptrSubHeaderTextAppearance me.videa.voice:ptrSubHeaderTextAppearance}</code></td><td> Base text color, typeface, size, and style for Header and Footer Loading Views Sub Header </td></tr>
           </table>
           @see #PullToRefresh_ptrAdapterViewBackground
           @see #PullToRefresh_ptrAnimationStyle
           @see #PullToRefresh_ptrDrawable
           @see #PullToRefresh_ptrDrawableBottom
           @see #PullToRefresh_ptrDrawableEnd
           @see #PullToRefresh_ptrDrawableStart
           @see #PullToRefresh_ptrDrawableTop
           @see #PullToRefresh_ptrHeaderBackground
           @see #PullToRefresh_ptrHeaderSubTextColor
           @see #PullToRefresh_ptrHeaderTextAppearance
           @see #PullToRefresh_ptrHeaderTextColor
           @see #PullToRefresh_ptrListViewExtrasEnabled
           @see #PullToRefresh_ptrMode
           @see #PullToRefresh_ptrOverScroll
           @see #PullToRefresh_ptrRefreshableViewBackground
           @see #PullToRefresh_ptrRotateDrawableWhilePulling
           @see #PullToRefresh_ptrScrollingWhileRefreshingEnabled
           @see #PullToRefresh_ptrShowIndicator
           @see #PullToRefresh_ptrSubHeaderTextAppearance
         */
        public static final int[] PullToRefresh = {
            0x7f010000, 0x7f010001, 0x7f010002, 0x7f010003,
            0x7f010004, 0x7f010005, 0x7f010006, 0x7f010007,
            0x7f010008, 0x7f010009, 0x7f01000a, 0x7f01000b,
            0x7f01000c, 0x7f01000d, 0x7f01000e, 0x7f01000f,
            0x7f010010, 0x7f010011, 0x7f010012
        };
        /**
          <p>
          @attr description
           BELOW HERE ARE DEPRECEATED. DO NOT USE. 


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrAdapterViewBackground
        */
        public static final int PullToRefresh_ptrAdapterViewBackground = 16;
        /**
          <p>
          @attr description
           Style of Animation should be used displayed when pulling. 


          <p>Must be one or more (separated by '|') of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>rotate</code></td><td>0x0</td><td></td></tr>
<tr><td><code>flip</code></td><td>0x1</td><td></td></tr>
</table>
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrAnimationStyle
        */
        public static final int PullToRefresh_ptrAnimationStyle = 12;
        /**
          <p>
          @attr description
           Drawable to use as Loading Indicator. Changes both Header and Footer. 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrDrawable
        */
        public static final int PullToRefresh_ptrDrawable = 6;
        /**
          <p>This symbol is the offset where the {@link me.videa.voice.R.attr#ptrDrawableBottom}
          attribute's value can be found in the {@link #PullToRefresh} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name me.videa.voice:ptrDrawableBottom
        */
        public static final int PullToRefresh_ptrDrawableBottom = 18;
        /**
          <p>
          @attr description
           Drawable to use as Loading Indicator in the Footer View. Overrides value set in ptrDrawable. 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrDrawableEnd
        */
        public static final int PullToRefresh_ptrDrawableEnd = 8;
        /**
          <p>
          @attr description
           Drawable to use as Loading Indicator in the Header View. Overrides value set in ptrDrawable. 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrDrawableStart
        */
        public static final int PullToRefresh_ptrDrawableStart = 7;
        /**
          <p>This symbol is the offset where the {@link me.videa.voice.R.attr#ptrDrawableTop}
          attribute's value can be found in the {@link #PullToRefresh} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name me.videa.voice:ptrDrawableTop
        */
        public static final int PullToRefresh_ptrDrawableTop = 17;
        /**
          <p>
          @attr description
           A drawable to use as the background of the Header and Footer Loading Views 


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrHeaderBackground
        */
        public static final int PullToRefresh_ptrHeaderBackground = 1;
        /**
          <p>
          @attr description
           Text Color of the Header and Footer Loading Views Sub Header 


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrHeaderSubTextColor
        */
        public static final int PullToRefresh_ptrHeaderSubTextColor = 3;
        /**
          <p>
          @attr description
           Base text color, typeface, size, and style for Header and Footer Loading Views 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrHeaderTextAppearance
        */
        public static final int PullToRefresh_ptrHeaderTextAppearance = 10;
        /**
          <p>
          @attr description
           Text Color of the Header and Footer Loading Views 


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrHeaderTextColor
        */
        public static final int PullToRefresh_ptrHeaderTextColor = 2;
        /**
          <p>
          @attr description
          
        	Whether PullToRefreshListView has it's extras enabled. This allows the user to be 
        	able to scroll while refreshing, and behaves better. It acheives this by adding
        	Header and/or Footer Views to the ListView.
        


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrListViewExtrasEnabled
        */
        public static final int PullToRefresh_ptrListViewExtrasEnabled = 14;
        /**
          <p>
          @attr description
           Mode of Pull-to-Refresh that should be used 


          <p>Must be one or more (separated by '|') of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>disabled</code></td><td>0x0</td><td></td></tr>
<tr><td><code>pullFromStart</code></td><td>0x1</td><td></td></tr>
<tr><td><code>pullFromEnd</code></td><td>0x2</td><td></td></tr>
<tr><td><code>both</code></td><td>0x3</td><td></td></tr>
<tr><td><code>manualOnly</code></td><td>0x4</td><td></td></tr>
<tr><td><code>pullDownFromTop</code></td><td>0x1</td><td> These last two are depreacted </td></tr>
<tr><td><code>pullUpFromBottom</code></td><td>0x2</td><td></td></tr>
</table>
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrMode
        */
        public static final int PullToRefresh_ptrMode = 4;
        /**
          <p>
          @attr description
           Whether Android's built-in Over Scroll should be utilised for Pull-to-Refresh. 


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrOverScroll
        */
        public static final int PullToRefresh_ptrOverScroll = 9;
        /**
          <p>
          @attr description
           A drawable to use as the background of the Refreshable View 


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrRefreshableViewBackground
        */
        public static final int PullToRefresh_ptrRefreshableViewBackground = 0;
        /**
          <p>
          @attr description
          
        	Whether the Drawable should be continually rotated as you pull. This only
        	takes effect when using the 'Rotate' Animation Style.
        


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrRotateDrawableWhilePulling
        */
        public static final int PullToRefresh_ptrRotateDrawableWhilePulling = 15;
        /**
          <p>
          @attr description
           Whether the user can scroll while the View is Refreshing 


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrScrollingWhileRefreshingEnabled
        */
        public static final int PullToRefresh_ptrScrollingWhileRefreshingEnabled = 13;
        /**
          <p>
          @attr description
           Whether the Indicator overlay(s) should be used 


          <p>May be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
<p>May be a boolean value, either "<code>true</code>" or "<code>false</code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrShowIndicator
        */
        public static final int PullToRefresh_ptrShowIndicator = 5;
        /**
          <p>
          @attr description
           Base text color, typeface, size, and style for Header and Footer Loading Views Sub Header 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name me.videa.voice:ptrSubHeaderTextAppearance
        */
        public static final int PullToRefresh_ptrSubHeaderTextAppearance = 11;
    };
}
