import * as React from 'react';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';

export default function TitlebarBelowImageList() {
  return (
    <ImageList sx={{ width: 600, height: 700 }}>
      {itemData.map((item) => (
        <ImageListItem key={item.img}>
          <img
            src={`${item.img}?w=100&fit=crop&auto=format`}
            srcSet={`${item.img}?w=100&fit=crop&auto=format&dpr=2 1x`}
            alt={item.title}
            loading="lazy"
          />
          <ImageListItemBar
            title={item.title}
//            subtitle={<span>by: {item.author}</span>}
            position="below"
          />
        </ImageListItem>
      ))}
    </ImageList>
  );
}

const itemData = [
  {
    img: 'https://cdn-1.motorsport.com/images/mgl/6D1QDzV0/s600/matt-kew-s-first-time-on-f1-gr-1.webp',
    title: 'Season 2022'
  },
  {
    img: 'https://www.brembo.com/en/PublishingImages/car/formula-1/auto_formula1.png',
    title: 'Ferrari'
  },
  {
    img: 'https://www.topgear.com/sites/default/files/2022/11/1%20Sebastian%20Vettel.jpg?w=1784&h=1004',
    title: 'Donuts by Seb<3'
  },
  {
    img: 'https://www.topgear.com/sites/default/files/2022/11/2%20Sebastian%20Vettel.jpg?w=1784&h=1004',
    title: "Marks won't wash off"
  },
  {
    img: 'https://d3cm515ijfiu6w.cloudfront.net/wp-content/uploads/2022/05/07125050/charles-leclerc-leads-mercedes-and-pierre-gasly-miami-planetf1.jpg',
    title: 'F1 Track'
  },
  {
    img: 'https://m.media-amazon.com/images/I/81r5u1n2paL.jpg',
    title: '2022 Teams'
  },
  {
    img: 'https://i.dailymail.co.uk/i/pix/2017/04/12/11/3F2EA46300000578-4401632-image-a-12_1491993906726.jpg',
    title: 'Pit Stops'
  },
  {
    img: 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/george-russel-mercedes-amg-f1-team-mercedes-amg-f1-w13-e-news-photo-1656621604.jpg',
    title: 'Merc'
  },
  {
    img: 'https://www.formula1.com/content/dam/fom-website/manual/1950vs2020/GettyImages-56632709.jpg.transform/9col-retina/image.jpg',
    title: '1950 British Grand Prix Start'
  },
  {
    img: 'https://cdn.bleacherreport.net/images_root/slides/photos/000/427/932/Lauda_original.jpg?1286281842',
    title: "Niki Lauda's Comeback"
  },
  {
    img: 'https://d2t1xqejof9utc.cloudfront.net/screenshots/pics/569b9bec853bf2f2a43dc727882f641a/original.jpg',
    title: 'f1 car full chasis'
  },
  {
    img: 'https://cdn-1.motorsport.com/images/mgl/01WyQXeY/s1200/rear-of-the-grid-at-the-start--1.webp',
    title: 'rear of grid at start of lap formation'
  },
];