# JackAnnoDemo

This is a simple demo for the library jackanno.

* jackanno * 是一个简单的注解和绑定事件的库。

## SnapShot

![Support Library](https://github.com/crazy1235/JackAnnoDemo/blob/master/others/snap.gif)

## Use

### Activity

```
    @ViewInject(R.id.textView)
    private TextView textView;

    @ViewInject(R.id.button)
    private Button btn;

    @ViewInject(R.id.checkBox)
    private CheckBox checkBox;

    @ViewInject(R.id.linear_layout)
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        JackAnno.inject(this);
    }

    @OnClick({R.id.button, R.id.linear_layout})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
                break;
            case R.id.linear_layout:
                Toast.makeText(this, "linearlayout has been clicked.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.textView)
    public void onTextViewClick(View view) {
        Toast.makeText(this, "textView has been clicked", Toast.LENGTH_SHORT).show();
    }

```


### Fragment

```

    @ViewInject(R.id.ratingBar)
    private RatingBar ratingBar;

    @ViewInject(R.id.datePicker)
    private DatePicker datePicker;

    @ViewInject(R.id.button2)
    private Button button;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        JackAnno.inject(this, view);

        button.setText("jacksen");

        return view;
    }

    @OnClick(R.id.button2)
    private void onViewClick(View view) {
        Toast.makeText(getActivity(), "button2 click.", Toast.LENGTH_SHORT).show();
    }

```